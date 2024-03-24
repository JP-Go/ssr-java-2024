package com.jp.wesettle.api.exceptionhandler;

import java.net.URI;
import java.util.stream.Collectors;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.jp.wesettle.domain.exception.DomainRuleException;

import lombok.AllArgsConstructor;

/**
 * ApiExceptionHandler
 */
@RestControllerAdvice
@AllArgsConstructor
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    private final MessageSource messageSource;

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        ProblemDetail details = ProblemDetail.forStatus(status);

        details.setType(URI.create("https://wesettle.jp.com/errors/invalid-fields"));
        details.setTitle("Um ou mais campos estão inválidos.");
        var fields = ex.getBindingResult().getAllErrors().stream()
            .collect(Collectors.toMap(e -> ((FieldError) e).getField(), e -> messageSource.getMessage(e,LocaleContextHolder.getLocale())));
        details.setProperty("fields", fields);
        return super.handleExceptionInternal(ex, details, headers, status, request);
    }

    @ExceptionHandler(value = DomainRuleException.class)
    public ProblemDetail handleDomainException(DomainRuleException ex){
        ProblemDetail details = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        details.setTitle(ex.getMessage());
        details.setType(URI.create("https://wesettle.jp.com/errors/business-rule-violation"));
        return details;
    }

    @ExceptionHandler(value = DataIntegrityViolationException.class)
    public ProblemDetail handleIntegrityViolationException(DataIntegrityViolationException ex){
        ProblemDetail details = ProblemDetail.forStatus(HttpStatus.CONFLICT);
        details.setTitle("Recurso em uso");
        details.setType(URI.create("https://wesettle.jp.com/errors/business-rule-violation"));
        return details;

    }

}
