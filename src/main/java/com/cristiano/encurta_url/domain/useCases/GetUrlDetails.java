package com.cristiano.encurta_url.domain.useCases;

import com.cristiano.encurta_url.domain.dtos.GetUrlDetailsResponse;

public interface GetUrlDetails {
    public GetUrlDetailsResponse execute(String slug);
}
