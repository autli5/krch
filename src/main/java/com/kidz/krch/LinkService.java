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

        // 🔥 FIX: нормализация URL
        originalUrl = normalizeUrl(originalUrl);

        String shortCode = generateShortCode();

        ShortUrl shortUrl = new ShortUrl(originalUrl, shortCode);

        repo.save(shortUrl);

        return shortCode;
    }

    // 🔥 URL NORMALIZER (КЛЮЧЕВАЯ ЧАСТЬ)
    private String normalizeUrl(String url) {
        if (url == null || url.isBlank()) {
            return null;
        }

        url = url.trim();

        // если нет схемы — добавляем https
        if (!url.startsWith("http://") && !url.startsWith("https://")) {
            url = "https://" + url;
        }

        return url;
    }

    private static final String CHARS =
            "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz123456789";

    private static final Random RANDOM = new Random();

    public static String generateShortCode() {
        StringBuilder sb = new StringBuilder(6);

        for (int i = 0; i < 6; i++) {
            sb.append(CHARS.charAt(RANDOM.nextInt(CHARS.length())));
        }

        return sb.toString();
    }
}