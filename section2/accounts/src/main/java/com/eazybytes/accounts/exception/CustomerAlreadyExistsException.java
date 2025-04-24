package com.eazybytes.accounts.exception;

public class CustomerAlreadyExistsException  extends RuntimeException{

        // esta clase se encarga de lanzar una excepción si el cliente ya existe, el metodo super() llama al constructor de la clase padre, en este caso RuntimeException
        public CustomerAlreadyExistsException(String message) {
            super(message);
        }


}
