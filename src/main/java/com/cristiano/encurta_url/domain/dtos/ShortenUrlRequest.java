package com.cristiano.encurta_url.domain.dtos;

public record ShortenUrlRequest(
        String originalUrl,
        String slug
) {
}
