package com.kidz.krch;
import org.springframework.stereotype.Service;

@Service
public class LinkService {

    private final LinkRepository repo;

    public LinkService(LinkRepository repo) {
        this.repo = repo;
    }

    public String getUrl(String originalUrl) {
        return repo.findByShortCode(shortCode)
                .map(ShortUrl::getOriginalUrl)
                .orElse(null);
    }


}
