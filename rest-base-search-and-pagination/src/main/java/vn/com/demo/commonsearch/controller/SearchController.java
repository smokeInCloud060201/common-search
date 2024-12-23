package vn.com.demo.commonsearch.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.com.demo.commonsearch.search.dto.SearchRequest;
import vn.com.demo.commonsearch.search.service.SearchService;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/search")
public class SearchController {

    private final SearchService searchService;

    @GetMapping
    public ResponseEntity<Object> searchWorksite(@ModelAttribute SearchRequest request) {
        log.info("The request we got that is : {}", request);
        return ResponseEntity.ok().body("Ok");
    }

}
