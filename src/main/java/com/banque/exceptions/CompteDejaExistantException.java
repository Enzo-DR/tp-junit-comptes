package com.banque.exceptions;
public class CompteDejaExistantException extends RuntimeException {
    public CompteDejaExistantException(String message) {
        super(message);
    }
}
