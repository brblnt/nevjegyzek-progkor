package hu.nye.progkor.exception.handler;

import hu.nye.progkor.exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * WebExceptionHandler handle not found and internal server exceptions.
 */
@Slf4j
@RestControllerAdvice
public class WebExceptionHandler extends ResponseEntityExceptionHandler {

  @org.springframework.web.bind.annotation.ExceptionHandler(NotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public String handleNotFoundException(final NotFoundException e) {
    log.error("Not found exception:", e);
    return "404-es hiba!";
  }


  @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public String handleException(final Exception e) {
    log.error("Server error:", e);
    return "500-as hiba!";
  }

}


