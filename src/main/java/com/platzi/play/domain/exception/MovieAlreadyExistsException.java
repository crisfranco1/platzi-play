package com.platzi.play.domain.exception;

public class MovieAlreadyExistsException extends RuntimeException {
    public MovieAlreadyExistsException(String movieTitle) {
        super("Movie with title '" + movieTitle + "' already exists.");
    }
}
