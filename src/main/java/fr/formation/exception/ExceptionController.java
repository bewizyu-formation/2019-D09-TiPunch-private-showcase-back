package fr.formation.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class ExceptionController {

    //réponse d'erreur à la demande REST si element non trouvé
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorDto> handle(NotFoundException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorDto(e.getMessage()));
    }

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
