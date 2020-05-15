package dev.peertosir.publicstonks.psapi.exceptions.users;

import dev.peertosir.publicstonks.psapi.exceptions.general.PublicStonksException;
import org.springframework.http.HttpStatus;

public class UserAlreadyExistsException extends PublicStonksException {

    private static final long serialVersionUID = 5823152971554492639L;

    public UserAlreadyExistsException() {
        super("User with such email already exists");
        this.statusCode = HttpStatus.CONFLICT;
    }
}
