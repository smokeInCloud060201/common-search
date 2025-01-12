package org.example.graphql.services;


import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.RequiredArgsConstructor;
import org.example.graphql.dto.StudentProjection;
import org.example.graphql.repository.AccountRepository;
import org.springframework.graphql.data.query.QuerydslDataFetcher;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    public void searchingData(Class<?> clazz) {

         QuerydslDataFetcher.builder(accountRepository)
                .customizer((bindings, root) -> {

                    Predicate  predicate= bindings.bind().first(
                            (path, value) -> {
                                BooleanExpression p1 = new BooleanBuilder();
                                return
                            }
                    );
                })
                .projectAs(StudentProjection.class)
                .build();

    }
}
