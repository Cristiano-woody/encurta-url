package com.cristiano.encurta_url.application.useCases;

import com.cristiano.encurta_url.application.protocols.UrlRepository;
import com.cristiano.encurta_url.domain.dtos.ShortenUrlRequest;
import com.cristiano.encurta_url.domain.dtos.ShortenUrlResponse;
import com.cristiano.encurta_url.domain.entities.Url;
import com.cristiano.encurta_url.domain.exceptions.SlugAlreadyExists;
import com.cristiano.encurta_url.domain.useCases.ShortenUrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.Normalizer;
import java.util.Optional;
import java.util.UUID;

@Service
public class ShortenUrlImpl implements ShortenUrl {

    @Autowired
    private UrlRepository urlRepository;

    @Override
    public ShortenUrlResponse execute(ShortenUrlRequest shortenUrlRequest) {

        Url newUrl = new Url(UUID.randomUUID().toString(), shortenUrlRequest.originalUrl(), null);

        if(shortenUrlRequest.slug() == null || shortenUrlRequest.slug().isBlank()) {
            newUrl.setSlug(generateCodeSlug());
        }
        else {
            newUrl.setSlug(slugfy(shortenUrlRequest.slug()));
        }

        newUrl.validate();

        Optional<Url> urlAlredyExists = this.urlRepository.findBySlug(newUrl.getSlug());
        if(urlAlredyExists.isPresent()) {
            throw new SlugAlreadyExists();
        }

        this.urlRepository.save(newUrl);

        return new ShortenUrlResponse(
            newUrl.getUrl(), newUrl.getSlug(), newUrl.getClicks()
        );
    }

    private String slugfy(String slug) {
        String slugNormalized = Normalizer.normalize(slug, Normalizer.Form.NFD);

        return slugNormalized.replaceAll("\\p{InCOMBINING_DIACRITICAL_MARKS}+", "")
                .replaceAll("^\\w\\s", "")
                .replaceAll("\\s+", "")
                .toLowerCase();
    }

    private String generateCodeSlug() {
        return UUID.randomUUID().toString().substring(0, 4);
    }
}
