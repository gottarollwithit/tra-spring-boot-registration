package com.gottarollwithit.traspringbootregistration.service;

import com.gottarollwithit.traspringbootregistration.form.RegistrationForm;
import com.gottarollwithit.traspringbootregistration.model.Account;
import com.gottarollwithit.traspringbootregistration.model.Session;
import com.gottarollwithit.traspringbootregistration.repository.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.Optional;

@Slf4j
@Service
public class AccountServiceImpl implements AccountService, UserDetailsService {


    @Autowired
    private AccountRepository userRepository;

    @Override
    public Account findOneById(Long id) {
        log.debug("Getting user={}", id);
        return userRepository.findOneById(id);
    }

    @Override
    public Account getUserByUsername(String username) {
        log.debug("Getting user by username={}", username);
        return userRepository.findByUsername(username);
    }

    @Override
    public Account create(RegistrationForm form) {
        log.debug("Creating account with username={} and email={}", form.getUsername(), form.getEmail());
        Account user = new Account();
        user.setUsername(form.getUsername().trim());
        user.setPassword(new BCryptPasswordEncoder().encode(form.getPassword()));
        user.setEmail(form.getEmail());
        user.setFirstName(form.getFirstName());
        user.setLastName(form.getLastName());
        user.setDateOfBirth(form.getDateOfBirth());
        return userRepository.save(user);
    }

    @Override
    public Optional<Account> getUserByUsernameOrEmail(String username, String email) {
        log.debug("Gettin user by username={} or e-mail={}", username, email);
        return userRepository.findByUsernameOrEmail(username, email);
    }

    public Session loadUserByUsername(String username) throws UsernameNotFoundException {
        Account user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("No user found with username: " + username);
        }
        return new Session(user);
    }


    @PostConstruct
    public void initialize() {
        log.debug("UserService initialized, creating demo user with credentials demo/Demo1234");
        Account demoAccount = new Account();
        demoAccount.setUsername("demo");
        demoAccount.setPassword(new BCryptPasswordEncoder().encode("Demo1234"));
        demoAccount.setEmail("demo@localhost.com");
        demoAccount.setFirstName("Demo");
        demoAccount.setLastName("User");
        demoAccount.setDateOfBirth(LocalDate.of(1991, 12, 20));
        userRepository.save(demoAccount);
    }
}
