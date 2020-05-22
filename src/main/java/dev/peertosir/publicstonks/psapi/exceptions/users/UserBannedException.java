package dev.peertosir.publicstonks.psapi.exceptions.users;

import dev.peertosir.publicstonks.psapi.exceptions.general.PublicStonksException;
import org.springframework.http.HttpStatus;

public class UserBannedException extends PublicStonksException {

    private static final long serialVersionUID = -6057774973983249261L;

    public UserBannedException() {
        super("User was banned on this site");
        this.statusCode = HttpStatus.FORBIDDEN;
    }
}
