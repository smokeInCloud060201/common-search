package vn.com.demo.commonsearch.commons.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import vn.com.demo.commonsearch.commons.entity.BaseEntity;

@NoRepositoryBean
public interface BaseRepository<T extends BaseEntity, I> extends JpaRepository<T, I>, JpaSpecificationExecutor<T> {
}
