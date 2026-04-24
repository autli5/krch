package com.kidz.krch;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/links")
public class LinkController {

    private final LinkService linkService;

    public LinkController(LinkService linkService) {
        this.linkService = linkService;
    }

    // CREATE SHORT LINK
    @PostMapping
    public ResponseEntity<CreateLinkResponse> createShortLink(@RequestBody LinkRequest request) {

        String shortCode = linkService.addUrl(request.getOriginalUrl());

        String shortUrl = "http://localhost:8081/" + shortCode;

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new CreateLinkResponse(
                        shortCode,
                        request.getOriginalUrl(),
                        shortUrl
                ));
    }

    // INFO (JSON, для фронта)
    @GetMapping("/info/{shortCode}")
    public ResponseEntity<GetLinkResponse> getInfo(@PathVariable String shortCode) {

        String originalUrl = linkService.getUrl(shortCode);

        if (originalUrl == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(
                new GetLinkResponse(shortCode, originalUrl)
        );
    }
}