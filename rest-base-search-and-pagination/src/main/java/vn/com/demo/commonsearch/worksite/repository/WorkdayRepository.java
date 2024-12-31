package vn.com.demo.commonsearch.worksite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import vn.com.demo.commonsearch.worksite.entities.Workday;

@Repository
public interface WorkdayRepository extends JpaRepository<Workday, Long>, JpaSpecificationExecutor<Workday> {
}
