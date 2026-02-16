package com.kidz.krch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Random;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@SpringBootApplication
public class KrchApplication {
    //TODO : move logic to service



	public static void main(String[] args) {
		SpringApplication.run(KrchApplication.class, args);
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

   String addUrl(String originalUrl){
        String shortCode = generateShortCode();
        ShortUrl link = new ShortUrl(originalUrl, shortCode);
        repo.save(link);
        return link.getShortCode();
   }

    public String getUrl(String shortCode){
        
    }


}
