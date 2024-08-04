package com.cristiano.encurta_url.domain.dtos;

import java.time.LocalDateTime;

public record GetUrlDetailsResponse(
        String originalUrl,
        String slug,
        LocalDateTime updatedAt
) {
}
