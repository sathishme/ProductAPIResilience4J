package com.cognizant.cde.fego.samples.ProductAPIResilience4J;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching()
public class ProductApiResilience4JApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductApiResilience4JApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplateBuilder().build();
    }
}
