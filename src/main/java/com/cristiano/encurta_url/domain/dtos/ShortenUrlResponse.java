package com.cristiano.encurta_url.domain.dtos;

public record ShortenUrlResponse(
        String originalUrl,
        String slug,
        Integer clicks
) {
}
