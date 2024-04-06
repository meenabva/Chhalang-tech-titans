package com.he.veera.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.he.veera.SearchService;
import com.he.veera.dto.SearchResponse;
import com.he.veera.dto.SearchVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/search")
@Slf4j
public class SearchController {

    @Autowired
    private SearchService service;

    @PostMapping
    public SearchResponse getSearchResult(@RequestBody String vo) throws  JsonProcessingException {
        return service.getSearchResults(vo);
    }

}
