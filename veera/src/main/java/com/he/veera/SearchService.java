package com.he.veera;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.he.veera.dto.GoogleSearchItem;
import com.he.veera.dto.GoogleSearchResponse;
import com.he.veera.dto.SearchResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class SearchService {
    private final RestTemplate restTemplate;
    private final ObjectMapper mapper;
    private final OpenAIClient aiClient;
    @Value("${google.api.key}")
    private String apiKey;
    @Value("${google.api.cx}")
    private String cx;
    @Value("${google.api.url}")
    private String baseUrl;
    public SearchResponse getSearchResults(String query) throws JsonProcessingException {
        String url = baseUrl + "?key=" + apiKey + "&cx=" + cx + "&q=" + query;
        String response = restTemplate.getForObject(url, String.class);
        GoogleSearchResponse searchResponse = mapper.readValue(response, GoogleSearchResponse.class);
        List<String> links= new LinkedList<>();
        for (GoogleSearchItem string : searchResponse.getItems()) {
        	links.add(string.getLink());	
		}
        String result= aiClient.getSummary(links);
        return new SearchResponse(result);
    }
}