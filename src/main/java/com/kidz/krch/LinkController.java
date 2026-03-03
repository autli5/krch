package com.kidz.krch;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/links")
public class LinkController {

    private final LinkService linkService;

    public LinkController(LinkService linkService) {
        this.linkService = linkService;
    }

    @PostMapping
    public String createShortLink(@RequestBody String originalUrl) {
        return linkService.addUrl(originalUrl);
    }

    @GetMapping("/{shortCode}")
    public String getOriginalUrl(@PathVariable String shortCode) {
        return linkService.getUrl(shortCode);
    }
}