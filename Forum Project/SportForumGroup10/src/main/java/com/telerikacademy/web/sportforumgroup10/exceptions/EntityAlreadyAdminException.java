package com.telerikacademy.web.sportforumgroup10.exceptions;

public class EntityAlreadyAdminException extends RuntimeException{
    public EntityAlreadyAdminException(String type, String atribute, String value) {
        super(String.format("%s with %s %s is already admin.", type, atribute, value));
    }
}
