package org.example.projecttwo;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice //?
public class ManejadorExcepciones {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> manejarValidacion(MethodArgumentNotValidException ex){
        String mensajeError=ex.getBindingResult().getFieldError().getDefaultMessage();
        String campo=ex.getBindingResult().getFieldError().getField();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(campo+": "+mensajeError);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String> manejarConstraint(ConstraintViolationException ex){

        String errores = ex.getConstraintViolations().stream().map(v -> v.getPropertyPath() + ": " + v.getMessage())
                .reduce("", (a,b) -> a + b + "\n");

        return ResponseEntity.badRequest().body(errores);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> manejarFormatoInvalido(HttpMessageNotReadableException ex){
        String mensaje=ex.getMessage();

        //debug
        /*System.out.println("EX:");
        System.out.println(ex.getMessage());

        System.out.println("CAUSE:");
        System.out.println(ex.getMostSpecificCause().getMessage());

        return ResponseEntity.badRequest().body(mensaje);*/

        if (mensaje.contains("LocalTime")){
            return ResponseEntity.badRequest().body("Formato inválido para hora. Use HH:MM (ej: 14:30)...");
        }

        if (mensaje.contains("LocalDate")){
            return ResponseEntity.badRequest().body("Formato inválido para fecha. Use AAAA-MM-DD (ej: 2026-12-25)...");
        }

        if (mensaje.contains("JSON")||mensaje.contains("Unexpected")||mensaje.contains("Unrecognized")||mensaje.contains("EOF")){
            return ResponseEntity.badRequest()
                    .body("JSON inválido o mal formado. Revise comas, comillas y estructura...");
        }

        return ResponseEntity.badRequest().body("Error en el formato del request.");
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> manejarGeneral(Exception ex){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno...");
    }
}