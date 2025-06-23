package rw.productant.v1.common.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import rw.productant.v1.common.dtos.ErrorResponse;
import rw.productant.v1.common.dtos.Response;
import rw.productant.v1.common.enums.EResponseType;

import java.util.ArrayList;
import java.util.List;

public class TokenException extends Exception{
    private final HttpStatus httpStatus = HttpStatus.UNAUTHORIZED;

    public TokenException(String message){
        super(message);
    }

    public ResponseEntity<?> getResponseEntity() {
        List<String> details = new ArrayList<>();
        details.add(super.getMessage());
        ErrorResponse errorResponse = new ErrorResponse().setMessage("You do not have authority to access this resources").setDetails(details);
        Response<ErrorResponse> response = new Response<>();
        response.setPayload(errorResponse);
        response.setType(EResponseType.UNAUTHORIZED);
        return new ResponseEntity<Response>(response , httpStatus);
    }
}