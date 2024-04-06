package com.he.veera;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.he.veera.dto.OpenAIRequest;
import com.he.veera.dto.OpenAIResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class OpenAIClient {
    private final ObjectMapper objectMapper;
    private final RestTemplate template;
    @Value("${openai.question}")
    private String request;
    @Value("${openai.api.url}")
    private String apiUrl;
    @Value("${open.api.key}")
    private String apiKey;
    public String getSummary(List<String> links) throws JsonProcessingException {
        String content = getContent(links);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiKey);
        OpenAIRequest openAIRequest = OpenAIRequest.builder()
                .model("gpt-3.5-turbo")
                .messages(List.of(OpenAIRequest.Message.builder()
                        .role("user")
                        .content(content)
                        .build()))
                .temperature(1.0)
                .top_p(1.0)
                .n(1)
                .stream(false)
                .presence_penalty(0)
                .frequency_penalty(0)
                .build();
        String stringRequest = objectMapper.writeValueAsString(openAIRequest);
        HttpEntity<String> entity = new HttpEntity<>(stringRequest, headers);
        ResponseEntity<String> response = template.postForEntity(apiUrl, entity, String.class);
        OpenAIResponse openAIResponse = objectMapper.readValue(response.getBody(), OpenAIResponse.class);
        final String content1 = openAIResponse.getChoices().get(0).getMessage().getContent();
        log.info(content1);
        return content1;
    }
    private String getContent(List<String> links) {
        String linkCollection = String.join(" ", links);
        return request + linkCollection;
    }
}