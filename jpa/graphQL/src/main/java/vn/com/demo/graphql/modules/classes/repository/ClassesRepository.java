package vn.com.demo.graphql.modules.classes.repository;

import org.springframework.stereotype.Repository;
import vn.com.demo.graphql.commons.repository.BaseRepository;
import vn.com.demo.graphql.modules.classes.entity.Classes;

@Repository
public interface ClassesRepository extends BaseRepository<Classes, Long> {
}
