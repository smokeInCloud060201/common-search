package vn.com.demo.graphql.modules.student.repository;

import org.springframework.stereotype.Repository;
import vn.com.demo.graphql.commons.repository.BaseRepository;
import vn.com.demo.graphql.modules.student.entity.Student;

@Repository
public interface StudentRepository extends BaseRepository<Student, Long> {
}
