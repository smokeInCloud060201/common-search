package vn.com.demo.graphql.repository;

import vn.com.demo.graphql.entity.Student;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.Repository;
import org.springframework.graphql.data.GraphQlRepository;

@GraphQlRepository
public interface AccountRepository extends Repository<Student, Long>, QuerydslPredicateExecutor<Student> {
}
