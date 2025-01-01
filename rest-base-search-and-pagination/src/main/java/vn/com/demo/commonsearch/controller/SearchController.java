package vn.com.demo.commonsearch.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.com.demo.commonsearch.search.dto.SearchRequest;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/search")
public class SearchController {

    @GetMapping
    public ResponseEntity<Object> searchWorksite(@ModelAttribute SearchRequest request) {
        log.info("The request we got that is : {}", request);
        return ResponseEntity.ok().body("Ok");
    }

}
