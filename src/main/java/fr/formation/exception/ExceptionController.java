package fr.formation.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

/**
 * Handle the REST controller's exceptions
 */
@ControllerAdvice
public class ExceptionController {

    /**
     * Error response to REST request if element not found.
     * @param e NotFoundException
     * @return a ResponseEntity with an error status and error message and date in the body.
     */
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorDto> handle(NotFoundException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorDto(e.getMessage()));
    }

    /**
     * Error response to REST request if a value that must be unique already exists.
     * @param e AlreadyExistsException
     * @return a ResponseEntity with an error status and error message and date in the body.
     */
    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<ErrorDto> handle(AlreadyExistsException e){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorDto(e.getMessage()));
    }

    /**
     * Error response to REST request if the adress can't be read (if it isn't to the fomat : "18 Rue de la Garde, 69005 Lyon, France"
     * @param e LocalizationException
     * @return a ResponseEntity with an error status and error message and date in the body.
     */
    @ExceptionHandler(LocalizationException.class)
    public ResponseEntity<ErrorDto> handle(LocalizationException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDto(e.getMessage()));
    }

    /**
     * DTO class used to transmit the error message and date
     */
    private class ErrorDto{
        private String message;
        private Date date = new Date();

        public ErrorDto() {
        }

        public ErrorDto(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
        public Date getDate() {
            return date;
        }
    }
}
