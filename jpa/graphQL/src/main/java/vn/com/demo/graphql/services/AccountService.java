package vn.com.demo.graphql.services;


import lombok.RequiredArgsConstructor;
import vn.com.demo.graphql.repository.AccountRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    public void searchingData(Class<?> clazz) {

    }
}
