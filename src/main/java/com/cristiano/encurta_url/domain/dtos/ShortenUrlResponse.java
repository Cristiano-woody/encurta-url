package com.cristiano.encurta_url.domain.dtos;

public record ShortenUrlResponse(
        String originalUrl,
        String Slug,
        Integer clicks
) {
}
