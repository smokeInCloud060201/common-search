package vn.com.demo.commonsearch.modules.worksite.repository;

import org.springframework.stereotype.Repository;
import vn.com.demo.commonsearch.commons.repository.BaseRepository;
import vn.com.demo.commonsearch.modules.worksite.entities.Workday;

@Repository
public interface WorkdayRepository extends BaseRepository<Workday, Long> {
}
