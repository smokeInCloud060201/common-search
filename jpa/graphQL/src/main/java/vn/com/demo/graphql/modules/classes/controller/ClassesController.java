package vn.com.demo.graphql.modules.classes.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.com.demo.graphql.commons.search.dto.SearchRequest;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/classes")
public class ClassesController {

    @GetMapping
    public ResponseEntity<Object> searchWorksite(@ModelAttribute SearchRequest request) {
        log.info("The request we got that is : {}", request);
        return ResponseEntity.ok().body("Ok");
    }

}
