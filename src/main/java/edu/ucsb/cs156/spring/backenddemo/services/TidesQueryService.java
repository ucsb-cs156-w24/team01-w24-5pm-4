package edu.ucsb.cs156.spring.backenddemo.services;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.web.client.RestTemplate;


import java.util.List;
import java.util.Map;


import org.springframework.boot.web.client.RestTemplateBuilder;

import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TidesQueryService {


    private final RestTemplate restTemplate;

    public TidesQueryService(RestTemplateBuilder restTemplateBuilder) {
        restTemplate = restTemplateBuilder.build();
    }

    // Documentation for endpoint is at: https://api.tidesandcurrents.noaa.gov/api/prod/
    public static final String ENDPOINT = "https://api.tidesandcurrents.noaa.gov/api/prod/datagetter?application=ucsb-cs156&begin_date={beginDate}&end_date={endDate}&station={station}&product=predictions&datum=mllw&units=english&time_zone=lst_ldt&interval=hilo&format=json";

    public String getJSON(String beginDate, String endDate, String station) throws HttpClientErrorException {
        log.info("beginDate={}", beginDate);
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, String> uriVariables = Map.of("beginDate", beginDate, "endDate", endDate, "station", station);
        HttpEntity<String> entity = new HttpEntity<>("body", headers);
        ResponseEntity<String> re = restTemplate.exchange(ENDPOINT, HttpMethod.GET, entity, String.class, uriVariables);
        return re.getBody();
    }
}