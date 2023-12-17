package cinema;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Stats {

    @JsonProperty("income")
    private int income;

    @JsonProperty("available")
    private int available;

    @JsonProperty("purchased")
    private int purchased;

    public Stats(int income, int available, int purchased) {
        this.income = income;
        this.available = available;
        this.purchased = purchased;
    }
}
