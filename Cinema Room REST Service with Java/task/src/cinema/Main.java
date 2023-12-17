package cinema;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}


@RestController
class MyController {
    private static final String SUPER_SECRET_PASSWORD = "super_secret";
    private final SeatMap seatMap;
    MyController(SeatMap seatMap) {
        this.seatMap = seatMap;
    }

    @GetMapping("/seats")
    public ReducedSeatMap getData() {
        return new ReducedSeatMap(
                seatMap.getRows(),
                seatMap.getColumns(),
                seatMap.getSeats()
        );
    }

    @PostMapping("/purchase")
    public ResponseEntity<Object> handlePurchaseRequest(@RequestBody PurchaseRequest request) {
        int row = request.getRow();
        int column = request.getColumn();
        Seat foundSeat = seatMap.findSeatByRowAndColumn(row, column);
        if (foundSeat == null) {
            ErrorResponse errorResponse = new ErrorResponse("The number of a row or a column is out of bounds!");
            return ResponseEntity.badRequest().body(errorResponse);
        }
        int seatHash = Integer.parseInt(Integer.toString(row) + Integer.toString(column));
        if (seatMap.checkBooking(row, column)) {
            ErrorResponse errorResponse = new ErrorResponse("The ticket has been already purchased!");
            return ResponseEntity.badRequest().body(errorResponse);
        }
        seatMap.bookSeat(seatHash);
        String tokenString = seatMap.addTicket(foundSeat);
        TicketResponse ticketResponse = new TicketResponse(tokenString, foundSeat);
        return ResponseEntity.ok(ticketResponse);
    }

    @PostMapping("/return")
    public ResponseEntity<Object> handleReturnRequest(@RequestBody ReturnRequest request) {
        String tokenString = request.getToken();
        Seat seat = seatMap.returnTicket(tokenString);
        if (seat == null) {
            ErrorResponse errorResponse = new ErrorResponse("Wrong token!");
            return ResponseEntity.badRequest().body(errorResponse);
        }
        ReturnResponse returnResponse = new ReturnResponse(seat);
        return ResponseEntity.ok(returnResponse);
    }


    @GetMapping("/stats")
    public ResponseEntity<Object> getStats(@RequestParam(required = false) String password) {
        if (SUPER_SECRET_PASSWORD.equals(password)) {
            Stats stats = new Stats(seatMap.getIncome(), seatMap.getAvailable(), seatMap.getPurchased());
            return ResponseEntity.ok(stats);
        } else {
            ErrorResponse errorResponse = new ErrorResponse("The password is wrong!");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(errorResponse);
        }
    }

}



