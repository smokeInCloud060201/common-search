package org.example.graphql.services;

import lombok.RequiredArgsConstructor;
import org.example.graphql.dto.StudentProjection;
import org.example.graphql.repository.AccountRepository;
import org.springframework.graphql.data.query.QuerydslDataFetcher;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    public void searchingData() {
        QuerydslDataFetcher.builder(accountRepository)
                .customizer((bindings, root) -> {
                    bindings.
                })
                .projectAs(StudentProjection.class).single();
    }
}
