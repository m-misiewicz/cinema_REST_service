package cinema;


import com.fasterxml.jackson.annotation.JsonProperty;
public class ReturnRequest {

    public String getToken() {
        return token;
    }

    @JsonProperty("token")
    private String token;


}
