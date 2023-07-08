package telran.java47.book.dto.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class AuthorExistsException extends RuntimeException {
    private static final long serialVersionUID = -7512253034485612311L;
}
