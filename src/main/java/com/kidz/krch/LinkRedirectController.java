package com.kidz.krch;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class LinkRedirectController {

    private final LinkService linkService;

    public LinkRedirectController(LinkService linkService) {
        this.linkService = linkService;
    }

    @GetMapping("/{shortCode}")
    public ResponseEntity<Void> redirect(@PathVariable String shortCode) {

        String originalUrl = linkService.getUrl(shortCode);

        if (originalUrl == null) {
            return ResponseEntity.notFound().build();
        }

        // защита + нормализация
        if (!originalUrl.startsWith("http://") && !originalUrl.startsWith("https://")) {
            originalUrl = "https://" + originalUrl;
        }

        return ResponseEntity
                .status(HttpStatus.FOUND) // 302 (лучше чем 301)
                .header("Location", originalUrl)
                .build();
    }
}