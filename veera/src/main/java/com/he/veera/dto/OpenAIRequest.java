package com.he.veera.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class OpenAIRequest {
    private String model;
    private List<Message> messages;
    private double temperature;
    private double top_p;
    private int n;
    private boolean stream;
    private double presence_penalty;
    private double frequency_penalty;

    @Data
    @Builder
    public static class Message {
        private String role;
        private String content;
    }
}