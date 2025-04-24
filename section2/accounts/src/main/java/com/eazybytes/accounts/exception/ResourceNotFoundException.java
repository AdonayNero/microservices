package com.eazybytes.accounts.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{

        // esta clase se encarga de lanzar una excepción si el cliente ya existe, el metodo super() llama al constructor de la clase padre, en este caso RuntimeException
        public ResourceNotFoundException(String resource, String fieldName, String fieldValue) {

            super(String.format("%s not found with %s : '%s'", resource, fieldName, fieldValue));
        }


}
