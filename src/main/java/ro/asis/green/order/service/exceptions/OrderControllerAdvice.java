package ro.asis.green.order.service.exceptions;

import lombok.Value;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class OrderControllerAdvice {

    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler(OrderNotFoundException.class)
    public ApiError handleOrderNotFoundException(OrderNotFoundException exception) {
        return new ApiError(exception.getMessage());
    }

}

@Value
class ApiError {
    String message;
}
