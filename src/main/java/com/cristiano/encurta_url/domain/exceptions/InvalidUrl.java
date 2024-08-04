package com.cristiano.encurta_url.domain.exceptions;

public class InvalidUrl extends RuntimeException {
    public InvalidUrl() {
        super("A Url fornecida é inválida.");
    }
}
