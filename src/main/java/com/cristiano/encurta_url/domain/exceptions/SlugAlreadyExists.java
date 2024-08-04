package com.cristiano.encurta_url.domain.exceptions;

public class SlugAlreadyExists extends RuntimeException {
    public SlugAlreadyExists() {
        super("Apelido escolhido já está registrado.");
    }
}
