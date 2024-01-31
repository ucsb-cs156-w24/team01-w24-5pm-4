package edu.ucsb.cs156.spring.backenddemo.controllers;

import edu.ucsb.cs156.spring.backenddemo.services.JokeQueryService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;


@Tag(name="Jokes from https://v2.jokeapi.dev/")
@Slf4j
@RestController
@RequestMapping("/api/jokes")
public class JokeController {

    ObjectMapper mapper = new ObjectMapper();

    @Autowired
    JokeQueryService JokeQueryService;

    @Operation(summary = "Get jokes from a given category and amount", 
    description = "JSON return format documented here:  https://v2.jokeapi.dev/")
    
    @GetMapping("/get")

    public ResponseEntity<String> getJokesQuery(
        @Parameter(name="category", example="Programming") @RequestParam String category,
        @Parameter(name="numJokes", example="2") @RequestParam String numJokes
    ) throws JsonProcessingException {
        log.info("getJokesQuery: category={}, numJokes={}", category,numJokes);
        String result = JokeQueryService.getJSON(category, numJokes);
        return ResponseEntity.ok().body(result);
    }
}

