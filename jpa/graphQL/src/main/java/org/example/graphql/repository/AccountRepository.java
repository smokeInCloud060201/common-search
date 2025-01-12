package org.example.graphql.repository;

import org.example.graphql.dto.StudentProjection;
import org.example.graphql.entity.Student;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.Repository;
import org.springframework.graphql.data.GraphQlRepository;

@GraphQlRepository
public interface AccountRepository extends Repository<Student, Long>, QuerydslPredicateExecutor<Student> {
}
