package fr.formation.exception;

public class LocalizationException extends RuntimeException {
    public LocalizationException() {
    }

    public LocalizationException(String message) {
        super(message);
    }
}
