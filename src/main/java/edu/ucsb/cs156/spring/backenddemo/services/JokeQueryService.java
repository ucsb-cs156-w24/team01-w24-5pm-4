package edu.ucsb.cs156.spring.backenddemo.services;

import lombok.extern.slf4j.Slf4j;

import java.util.Map;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;


@Slf4j
@Service
public class JokeQueryService {

    private final RestTemplate restTemplate;

    public JokeQueryService(RestTemplateBuilder restTemplateBuilder) {
        restTemplate = restTemplateBuilder.build();
    }

    public static final String ENDPOINT = "https://v2.jokeapi.dev/joke/{category}?amount={numJokes}";

    public String getJSON(String category, String numJokes) throws HttpClientErrorException {
        log.info("category={}, numJokes={}", category, numJokes);

        Map<String, String> uriVariables = Map.of("category", category, "numJokes", numJokes);
        return "";
    }
}