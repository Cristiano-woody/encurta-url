package com.cristiano.encurta_url.domain.entities;

import com.cristiano.encurta_url.domain.exceptions.InvalidUrl;
import jakarta.persistence.*;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.LocalDateTime;

@Entity
public class Url {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "original_url", nullable = false)
    private String url;

    @Column(name = "slug", nullable = false, unique = true)
    private String slug;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @Column(name = "clicks", nullable = false)
    private Integer clicks = 0;

    public Url(String id, String url, String slug) {
        this.id = id;
        this.url = url;
        this.slug = slug;
        this.updatedAt = LocalDateTime.now();
    }

    public Url() {
    }

    public void validate() {
        if (this.id == null || this.id.isBlank())
            throw new IllegalArgumentException("Id cannot be blank");

        if(this.url == null || this.url.isBlank())
            throw new IllegalArgumentException("Url cannot be blank");

        if(this.slug == null || this.slug.isBlank())
            throw new IllegalArgumentException("Slug cannot be blank");

        if(this.updatedAt == null)
            throw new IllegalArgumentException("UpdatedAt cannot be null");

        this.validateUrl();
    }

    private void validateUrl() {
        try {
            new URL(this.url).toURI();
        } catch (URISyntaxException | MalformedURLException e) {
            throw new InvalidUrl();
        }
    }

    public void addClick() {
        this.clicks++;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String surname) {
        this.slug = surname;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Integer getClicks() {
        return clicks;
    }

    public void setClicks(Integer clicks) {
        this.clicks = clicks;
    }
}
