package org.example.graphql.services;


import lombok.RequiredArgsConstructor;
import org.example.graphql.repository.AccountRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    public void searchingData(Class<?> clazz) {

    }
}
