package com.cristiano.encurta_url.domain.exceptions;

public class UrlNotFound extends RuntimeException {
    public UrlNotFound() {
        super("Url buscada não foi encontrada.");
    }
}
