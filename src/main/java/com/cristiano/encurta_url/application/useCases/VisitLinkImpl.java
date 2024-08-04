package com.cristiano.encurta_url.application.useCases;

import com.cristiano.encurta_url.application.protocols.UrlRepository;
import com.cristiano.encurta_url.domain.dtos.GetUrlDetailsResponse;
import com.cristiano.encurta_url.domain.entities.Url;
import com.cristiano.encurta_url.domain.exceptions.UrlNotFound;
import com.cristiano.encurta_url.domain.useCases.VisitLink;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VisitLinkImpl implements VisitLink {

    @Autowired
    private UrlRepository urlRepository;

    @Override
    public String execute(String slug) {
        if(slug == null || slug.isBlank()) {
            throw new IllegalArgumentException("Slug cannot be null or blank");
        }

        Optional<Url> url = urlRepository.findBySlug(slug);
        if(url.isEmpty()) {
            throw new UrlNotFound();
        }

        url.get().addClick();
        urlRepository.save(url.get());

        return url.get().getUrl();
    }
}
