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
import java.util.Map;


@ControllerAdvice
public class ExceptionsUtils {  // Global Exception Handler
       @ExceptionHandler(Exception.class)
        public static  <T> ResponseEntity<ApiResponsePayload> handleControllerExceptions(Exception e) {
           System.out.println(e);
        if (e instanceof ChangeSetPersister.NotFoundException) {
            return new ResponseEntity<>(new ApiResponsePayload(
                    false,
                    e.getMessage()
            ), HttpStatus.NOT_FOUND);
        } else if(e instanceof InvalidUUIdException){
            return new ResponseEntity<>(new ApiResponsePayload(
                    false,
                    e.getMessage()
            ), HttpStatus.BAD_REQUEST);
        }else if(e instanceof NotFoundException){
            return new ResponseEntity<>(new ApiResponsePayload(
                    false,
                    e.getMessage()
            ), HttpStatus.NOT_FOUND);
        } else if (e instanceof IllegalArgumentException){
            return new ResponseEntity<>(new ApiResponsePayload(
                    false,
                    e.getMessage()
            ), HttpStatus.BAD_REQUEST);
        }else if (e instanceof HttpServerErrorException.InternalServerError) {
            return new ResponseEntity<>(new ApiResponsePayload(
                    false,
                    e.getMessage()
            ), HttpStatus.INTERNAL_SERVER_ERROR);
        } else if(e instanceof BadRequestException){
            return new ResponseEntity<>(new ApiResponsePayload(
                    false,
                    e.getMessage()
            ), HttpStatus.BAD_REQUEST);
        }else  if(e instanceof UnauthorizedException){
            return new ResponseEntity<>(new ApiResponsePayload(
                    false,
                    "You are not allowed to access this resource"
            ), HttpStatus.UNAUTHORIZED);
        } else {
            // Handle other exceptions as needed
            return new ResponseEntity<>(new ApiResponsePayload(
                    false,
                    e.getMessage()
            ), HttpStatus.INTERNAL_SERVER_ERROR);
        }
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
        errors.forEach((field, message) ->
                combinedErrors.append("[").append(field).append(": ").append(message).append("] ")
        );

        return new ResponseEntity<>(
                new ApiResponsePayload(false, combinedErrors.toString().trim()),
                HttpStatus.BAD_REQUEST
        );
    }

    public static <T> void handleServiceExceptions(Exception e) {
        System.out.println("Exception caught in service:");
        if(e instanceof IllegalArgumentException){
            throw new IllegalArgumentException(e.getMessage());
        }else if( e instanceof NotFoundException){
            throw new NotFoundException(e.getMessage());
        }else if (e instanceof HttpServerErrorException) {
            throw new InternalServerErrorException(e.getMessage());
        } else if (e instanceof BadRequestException){
            throw new BadRequestException(e.getMessage());
        }else  if(e instanceof UnauthorizedException){
            throw  new UnauthorizedException("You are not allowed to access this resource");
        } else {
            throw new RuntimeException("Failed!! Something went wrong " + e.getMessage(), e);
        }
    }
}
