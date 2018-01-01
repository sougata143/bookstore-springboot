package com.sougata.bookstore.securityConfig.utils;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class DtoValidationException extends RuntimeException{
    private static final long serialVersionUID = 5566743403610455711L;

    /**
     * Binding result field to keep a track of all the DTO Validation errors
     */
    private BindingResult bindingResult;

    /**
     *
     * @param message
     */
    public DtoValidationException(String message, BindingResult bindingResult){
        super(message);
        this.bindingResult = bindingResult;
    }

    /**
     *
     * @param bindingResult
     */
    public DtoValidationException(BindingResult bindingResult){
        super("Request payload validation errors");
        this.bindingResult = bindingResult;
    }

    public BindingResult getBindingResult() {
        return bindingResult;
    }
}
