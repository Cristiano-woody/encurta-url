package com.cristiano.encurta_url.infra.controllers;

import com.cristiano.encurta_url.domain.dtos.ShortenUrlRequest;
import com.cristiano.encurta_url.domain.exceptions.SlugAlreadyExists;
import com.cristiano.encurta_url.domain.exceptions.UrlNotFound;
import com.cristiano.encurta_url.domain.useCases.GetUrlDetails;
import com.cristiano.encurta_url.domain.useCases.ShortenUrl;
import com.cristiano.encurta_url.domain.useCases.VisitLink;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/shortner")
public class UrlController {

    @Autowired
    private ShortenUrl shortenUrl;
    @Autowired
    private GetUrlDetails getUrlDetails;
    @Autowired
    private VisitLink visitLink;

    @PostMapping
    public ResponseEntity<?> shortenUrl(@RequestBody ShortenUrlRequest request) {
        try {
            var response = this.shortenUrl.execute(request);
            return ResponseEntity.status(201).body(response);
        } catch (SlugAlreadyExists e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("{slug}")
    public ResponseEntity<?> getUrl(@PathVariable("slug") String slug, HttpServletResponse servletResponse) {
        try {
            System.out.println(slug);
            var originalUrl = this.visitLink.execute(slug);
            servletResponse.sendRedirect(originalUrl);
            return ResponseEntity.status(301).build();
        } catch (UrlNotFound e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("{slug}/details")
    public ResponseEntity<?> getUrlDetails(@PathVariable("slug") String slug) {
        try {
            var response = this.getUrlDetails.execute(slug);
            return ResponseEntity.status(200).body(response);
        } catch (UrlNotFound e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("/ping")
    public ResponseEntity<?> pong() {
        return ResponseEntity.ok().body("Pong.");
    }
}
