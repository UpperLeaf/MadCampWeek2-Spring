package com.example.demo.user;

import com.example.demo.auth.AuthUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AccountService implements UserDetailsService {

    private final AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return accountRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(email +" is not exist"));
    }

    public Account createAccount(AuthUser authUser) {
        Account account = new Account();
        account.setEmail(authUser.getEmail());
        account.setName(authUser.getName());
        return accountRepository.save(account);
    }

    public Account findByEmail(String email) {
        return accountRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(email + " is not found"));
    }
}
