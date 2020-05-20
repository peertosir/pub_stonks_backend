package dev.peertosir.publicstonks.psapi.exceptions.projects;

import org.springframework.http.HttpStatus;

import dev.peertosir.publicstonks.psapi.exceptions.general.PublicStonksException;

public class ProjectAlreadyExistsException extends PublicStonksException {

    private static final long serialVersionUID = -830756450929866043L;

    public ProjectAlreadyExistsException() {
        super("Project with such title already exists");
        this.statusCode = HttpStatus.CONFLICT;
    }
}