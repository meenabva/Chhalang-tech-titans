package com.he.veera.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class GoogleSearchResponse {
    private List<GoogleSearchItem> items;
}
