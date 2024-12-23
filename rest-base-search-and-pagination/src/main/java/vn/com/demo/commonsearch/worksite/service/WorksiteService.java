package vn.com.demo.commonsearch.worksite.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import vn.com.demo.commonsearch.worksite.entities.Worksite;
import vn.com.demo.commonsearch.worksite.repository.WorksiteRepository;

@Service
@RequiredArgsConstructor
public class WorksiteService {

    private final WorksiteRepository worksiteRepository;


    public Page<Worksite> searchWorksite() {


        return null;
    }
}
