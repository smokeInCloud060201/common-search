package vn.com.demo.commonsearch.repository;

import org.springframework.stereotype.Repository;
import vn.com.demo.commonsearch.base.BaseRepository;
import vn.com.demo.commonsearch.entities.Worksite;

@Repository
public interface WorksiteRepository extends BaseRepository<Worksite, Long> {
}
