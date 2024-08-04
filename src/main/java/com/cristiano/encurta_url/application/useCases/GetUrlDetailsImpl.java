package com.cristiano.encurta_url.application.useCases;

import com.cristiano.encurta_url.application.protocols.UrlRepository;
import com.cristiano.encurta_url.domain.dtos.GetUrlDetailsResponse;
import com.cristiano.encurta_url.domain.entities.Url;
import com.cristiano.encurta_url.domain.exceptions.UrlNotFound;
import com.cristiano.encurta_url.domain.useCases.GetUrlDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GetUrlDetailsImpl implements GetUrlDetails {

    @Autowired
    private UrlRepository urlRepository;

    @Override
    public GetUrlDetailsResponse execute(String slug) {
        if(slug == null || slug.isBlank()) {
            throw new IllegalArgumentException("Slug cannot be null or blank");
        }

        Optional<Url> url = urlRepository.findBySlug(slug);

        if(url.isEmpty()) {
            throw new UrlNotFound();
        }

        return new GetUrlDetailsResponse(
                url.get().getUrl(),
                url.get().getSlug(),
                url.get().getUpdatedAt(),
                url.get().getClicks()
        );
    }
}
