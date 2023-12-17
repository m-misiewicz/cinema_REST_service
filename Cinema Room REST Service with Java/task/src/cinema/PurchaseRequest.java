package cinema;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PurchaseRequest {
    @JsonProperty("row")
    private int row;

    @JsonProperty("column")
    private int column;

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }
}
