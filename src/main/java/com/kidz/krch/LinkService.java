package com.kidz.krch;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class LinkService {

    private final LinkRepository repo;

    public LinkService(LinkRepository repo) {
        this.repo = repo;
    }

    public String getUrl(String shortCode) {
        return repo.findByShortCode(shortCode)
                .map(ShortUrl::getOriginalUrl)
                .orElse(null);
    }

    public String addUrl(String originalUrl) {
        String shortCode = generateShortCode();
        ShortUrl shortUrl = new ShortUrl(originalUrl, shortCode);
        repo.save(shortUrl);
        return shortCode;
    }

    private static final String CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz123456789";
    private static final Random RANDOM = new Random();

    public static String generateShortCode (){
        StringBuilder sb = new StringBuilder(6);
        for (int i = 0; i < 6; i++) {
            sb.append(CHARS.charAt(RANDOM.nextInt(CHARS.length())));
        }
        return sb.toString();
    }
}
