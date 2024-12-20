package vn.com.demo.commonsearch.base;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseRepository<T extends BaseEntity, I> extends JpaRepository<T, I>, JpaSpecificationExecutor<T> {
}
