package com.gottarollwithit.traspringbootregistration.service;

import com.gottarollwithit.traspringbootregistration.form.RegistrationForm;
import com.gottarollwithit.traspringbootregistration.model.Account;

import java.util.Optional;

public interface AccountService {

    Account findOneById(Long id);

    Account getUserByUsername(String username);

    Account create(RegistrationForm form);

    Optional<Account> getUserByUsernameOrEmail(String username, String email);
}
