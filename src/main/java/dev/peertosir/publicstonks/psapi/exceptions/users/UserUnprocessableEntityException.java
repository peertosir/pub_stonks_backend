package dev.peertosir.publicstonks.psapi.exceptions.users;

import dev.peertosir.publicstonks.psapi.exceptions.general.PublicStonksException;
import org.springframework.http.HttpStatus;

public class UserUnprocessableEntityException extends PublicStonksException {

    private static final long serialVersionUID = -567570952814079605L;

    public UserUnprocessableEntityException() {
        super("Operation with user cannot be done due to restrictions");
        this.statusCode = HttpStatus.UNPROCESSABLE_ENTITY;
    }
}
