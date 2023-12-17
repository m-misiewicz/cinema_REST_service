package cinema;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ReducedSeatMap {

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    @JsonProperty("rows")
    private int rows;

    @JsonProperty("columns")
    private int columns;

    @JsonProperty("seats")
    private List<Seat> seats;

    // Getter and setter methods for the entries...

    public ReducedSeatMap(int rows, int columns, List<Seat> seats) {
        this.rows = rows;
        this.columns = columns;
        this.seats = seats;
    }


}
