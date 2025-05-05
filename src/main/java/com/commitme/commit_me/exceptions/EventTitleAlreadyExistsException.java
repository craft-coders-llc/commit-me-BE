package com.commitme.commit_me.exceptions;

public class EventTitleAlreadyExistsException extends RuntimeException {
    
    public EventTitleAlreadyExistsException (String message) {
        super(message);
    }
}
