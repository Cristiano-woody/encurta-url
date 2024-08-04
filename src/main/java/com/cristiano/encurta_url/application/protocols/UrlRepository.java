package com.cristiano.encurta_url.application.protocols;

import com.cristiano.encurta_url.domain.entities.Url;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UrlRepository extends JpaRepository<Url, UUID> {

    Optional<Url> findBySlug(String slug);

    Url save(Url url);

}
