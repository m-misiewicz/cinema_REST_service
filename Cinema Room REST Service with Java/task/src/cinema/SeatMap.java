package cinema;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.UUID;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Service;

@Service
public class SeatMap {



    private int income = 0;
    private int available = 81;
    private int purchased = 0;

    @JsonProperty("rows")
    private int rows;

    @JsonProperty("columns")
    private int columns;

    @JsonProperty("seats")
    private List<Seat> seats;



    private ConcurrentHashMap<Integer, Boolean> bookedSeats = new ConcurrentHashMap<>();


    private ConcurrentHashMap<String, Seat> ticketMap = new ConcurrentHashMap<>();

    public SeatMap() {
        this.rows = 9;
        this.columns = 9;
        this.seats = new ArrayList<>();
        generateSeats();
        generateBookingMap();
    }

    private void generateSeats() {
        for (int row = 1; row <= rows; row++) {
            for (int column = 1; column <= columns; column++) {
                int price;
                if (row <= 4){
                    price = 10;
                } else {
                    price = 8;
                }

                Seat seat = new Seat(row, column, price);
                seats.add(seat);
            }
        }
    }

    private void generateBookingMap() {
        for (Seat seat : seats) {
            int row = seat.getRow();
            int column = seat.getColumn();
            int seatHash = generateSeatHash(row, column);
            bookedSeats.put(seatHash, false);
        }
    }

    private int generateSeatHash(int row, int column) {
        String concatenatedString = Integer.toString(row) + Integer.toString(column);
        return Integer.parseInt(concatenatedString);
    }

    public void bookSeat(int seatHash) {
        bookedSeats.computeIfPresent(seatHash, (s, booked) -> true);
    }

    private void unBookSeat(int seatHash) {
        bookedSeats.computeIfPresent(seatHash, (s, booked) -> false);
    }

    public String addTicket(Seat seat) {
        UUID token = UUID.randomUUID();
        String tokenString = token.toString();
        ticketMap.put(tokenString, seat);
        int price = seat.getPrice();
        this.income += price;
        this.available -= 1;
        this.purchased += 1;
        return tokenString;
    }

    public Seat returnTicket(String tokenString) {

        Seat seat = ticketMap.get(tokenString);

        if (seat != null) {
            ticketMap.remove(tokenString);
            int seatHash = generateSeatHash(seat.getRow(), seat.getColumn());
            this.unBookSeat(seatHash);
            int price = seat.getPrice();
            this.income -= price;
            this.available += 1;
            this.purchased -= 1;
            return seat;
        }

        return null;
    }

    public Boolean checkBooking(int row, int column) {
        int seatHash = generateSeatHash(row, column);
        return bookedSeats.get(seatHash);
    }



    public Seat findSeatByRowAndColumn(int row, int column) {
        for (Seat seat : seats) {
            if (seat.getRow() == row && seat.getColumn() == column) {
                return seat; // Return the found seat
            }
        }
        return null; // Return null if not found
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public ConcurrentHashMap<Integer, Boolean> getBookedSeats() {
        return bookedSeats;
    }

    public int getIncome() {
        return income;
    }

    public int getAvailable() {
        return available;
    }

    public int getPurchased() {
        return purchased;
    }

}
