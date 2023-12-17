package cinema;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Seat{

    @JsonProperty("row")
    private int row;

    @JsonProperty("column")
    private int column;

    @JsonProperty("price")
    private int price;

    public Seat(int row, int column, int price) {
        this.row = row;
        this.column = column;
        this.price = price;
    }

    public int getRow() {
        return row;
    }

    public int getColumn(){
        return column;
    }

    public int getPrice() {
        return price;
    }



}

