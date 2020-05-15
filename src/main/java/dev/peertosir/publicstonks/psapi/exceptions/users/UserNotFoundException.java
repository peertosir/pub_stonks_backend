package dev.peertosir.publicstonks.psapi.exceptions.users;

import dev.peertosir.publicstonks.psapi.exceptions.general.PublicStonksException;
import org.springframework.http.HttpStatus;

public class UserNotFoundException extends PublicStonksException {

    private static final long serialVersionUID = 5296901527089239874L;

    public UserNotFoundException() {
        super("User not found");
        this.statusCode = HttpStatus.NOT_FOUND;
    }
}
