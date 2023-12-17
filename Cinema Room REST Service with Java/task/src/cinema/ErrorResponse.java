package cinema;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ErrorResponse {
    @JsonProperty("error")
    private final String error;

    public ErrorResponse(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }
}
