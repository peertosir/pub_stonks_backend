package dev.peertosir.publicstonks.psapi.exceptions.general;

import org.springframework.http.HttpStatus;

public class PublicStonksException extends RuntimeException {

    private static final long serialVersionUID = -5173205593052041766L;
    protected HttpStatus statusCode;

    public PublicStonksException(String message) {
        super(message);
    }

    public PublicStonksException() {
    }

    public HttpStatus getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(HttpStatus statusCode) {
        this.statusCode = statusCode;
    }
}

