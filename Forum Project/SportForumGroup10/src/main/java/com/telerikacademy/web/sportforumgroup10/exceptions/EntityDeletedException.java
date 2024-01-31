package com.telerikacademy.web.sportforumgroup10.exceptions;

public class EntityDeletedException extends RuntimeException{
    public EntityDeletedException(String type, String atribute, String value) {
        super(String.format("%s with %s %s already deleted.", type, atribute, value));
    }
}
