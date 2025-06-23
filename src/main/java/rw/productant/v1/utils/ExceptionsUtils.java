package rw.productant.v1.utils;

import io.swagger.v3.oas.models.responses.ApiResponse;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpServerErrorException;
import rw.productant.v1.common.exceptions.*;
import rw.productant.v1.common.payloads.ApiResponsePayload;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionsUtils { // Global Exception Handler

    @ExceptionHandler(Exception.class)
    public static <T> ResponseEntity<ApiResponsePayload> handleControllerExceptions(Exception exception) {
        System.out.println(exception);

        // Map exception classes to HttpStatus
        Map<Class<? extends Exception>, HttpStatus> exceptionStatusMap = new LinkedHashMap<>();

        // Keep all possible exceptions with their statuses
        exceptionStatusMap.put(ChangeSetPersister.NotFoundException.class, HttpStatus.NOT_FOUND);
        exceptionStatusMap.put(InvalidUUIdException.class, HttpStatus.BAD_REQUEST);
        exceptionStatusMap.put(NotFoundException.class, HttpStatus.NOT_FOUND);
        exceptionStatusMap.put(IllegalArgumentException.class, HttpStatus.BAD_REQUEST);
        exceptionStatusMap.put(HttpServerErrorException.InternalServerError.class, HttpStatus.INTERNAL_SERVER_ERROR);
        exceptionStatusMap.put(BadRequestException.class, HttpStatus.BAD_REQUEST);
        exceptionStatusMap.put(UnauthorizedException.class, HttpStatus.UNAUTHORIZED);

        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR; // default fallback
        for (Map.Entry<Class<? extends Exception>, HttpStatus> entry : exceptionStatusMap.entrySet()) {
            if (entry.getKey().isInstance(exception)) {
                status = entry.getValue();
                break;
            }
        }
        String message = (exception instanceof UnauthorizedException)
                ? "You are not allowed to access this resource"
                : exception.getMessage();
        ApiResponsePayload payload = new ApiResponsePayload(false, message);
        return new ResponseEntity<>(payload, status);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponsePayload> handleValidationExceptions(MethodArgumentNotValidException exception) {
        Map<String, String> errors = new HashMap<>();

        exception.getBindingResult().getFieldErrors().forEach(error -> { // Get field errors
            String fieldName = error.getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        // Properly format errors
        StringBuilder combinedErrors = new StringBuilder("Validation failed: ");
        errors.forEach(
                (field, message) -> combinedErrors.append("[").append(field).append(": ").append(message).append("] "));

        return new ResponseEntity<>(
                new ApiResponsePayload(false, combinedErrors.toString().trim()),
                HttpStatus.BAD_REQUEST);
    }
}
