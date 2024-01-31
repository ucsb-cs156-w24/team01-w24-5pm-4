package edu.ucsb.cs156.spring.backenddemo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.ucsb.cs156.spring.backenddemo.services.EarthquakeQueryService;
import edu.ucsb.cs156.spring.backenddemo.services.ZipCodeQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;


@Tag(name="Zipcode info from USGS")
@Slf4j
@RestController
@RequestMapping("/api/zipcode")
public class ZipCodeController {
        
    ObjectMapper mapper = new ObjectMapper();

    @Autowired
    ZipCodeQueryService zipcodeQueryService;

    @Operation(summary = "Get information about a given zipcode", description = "JSON return format documented here: https://api.zippopotam.us/")
    @GetMapping("/get")
    public ResponseEntity<String> getZipcodes(
        @Parameter(name="zipcode", description="zipcode", example="93117") @RequestParam String zipcode
    ) throws JsonProcessingException {
        log.info("getZipcodes: zipcode={}", zipcode);
        String result = zipcodeQueryService.getJSON(zipcode);
        return ResponseEntity.ok().body(result);
    }
}
