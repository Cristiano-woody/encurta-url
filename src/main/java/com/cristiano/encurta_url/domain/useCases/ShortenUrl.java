package com.cristiano.encurta_url.domain.useCases;

import com.cristiano.encurta_url.domain.dtos.ShortenUrlRequest;
import com.cristiano.encurta_url.domain.dtos.ShortenUrlResponse;

public interface ShortenUrl {

    public ShortenUrlResponse execute(ShortenUrlRequest shortenUrlRequest);
}
