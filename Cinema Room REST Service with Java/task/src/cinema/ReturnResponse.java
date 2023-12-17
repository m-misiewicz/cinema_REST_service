package cinema;


import com.fasterxml.jackson.annotation.JsonProperty;

public class ReturnResponse {

    @JsonProperty("ticket")
    private Seat seat;
    public ReturnResponse( Seat seat) {
        this.seat = seat;
    }

}
