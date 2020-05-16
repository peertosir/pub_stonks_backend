package dev.peertosir.publicstonks.psapi.exceptions.users;

import dev.peertosir.publicstonks.psapi.exceptions.general.PublicStonksException;
import org.springframework.http.HttpStatus;

public class UserUnprocessableEntityException extends PublicStonksException {

    private static final long serialVersionUID = -567570952814079605L;

    public UserUnprocessableEntityException() {
        super("Not all mandatory fields for User were filled");
        this.statusCode = HttpStatus.UNPROCESSABLE_ENTITY;
    }
}
