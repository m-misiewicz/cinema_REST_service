package cinema;


import com.fasterxml.jackson.annotation.JsonProperty;
public class TicketResponse {

    @JsonProperty("token")
    private String token;

    public void setToken(String token) {
        this.token = token;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }
    @JsonProperty("ticket")
    private Seat seat;

    public String getToken() {
        return token;
    }

    public Seat getSeat() {
        return seat;
    }

    public TicketResponse(String token, Seat seat) {

        this.token = token;
        this.seat = seat;
    }
}
