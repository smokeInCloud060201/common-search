package vn.com.demo.commonsearch.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import vn.com.demo.commonsearch.search.service.SearchService;

@Service
@Slf4j
@RequiredArgsConstructor
public class SearchServiceImpl implements SearchService {


    public void searchForKey() {

        log.info("Starting the searching process...");

    }
}
