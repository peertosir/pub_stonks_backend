package dev.peertosir.publicstonks.psapi.exceptions.projects;

import org.springframework.http.HttpStatus;

import dev.peertosir.publicstonks.psapi.exceptions.general.PublicStonksException;

public class ProjectAlreadyExistsException extends PublicStonksException {

    public ProjectAlreadyExistsException()  {
        super("Project with such title already exists");
        this.statusCode = HttpStatus.CONFLICT;
    }
}