package dev.peertosir.publicstonks.psapi.model.response.errors;

import java.util.Date;

public class ErrorRepresentation {
    private Date timestamp;
    private String message;

    public ErrorRepresentation() {}

    public ErrorRepresentation(Date timestamp, String message) {
        this.timestamp = timestamp;
        this.message = message;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
