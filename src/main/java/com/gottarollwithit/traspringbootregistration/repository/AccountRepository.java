package com.gottarollwithit.traspringbootregistration.repository;

import com.gottarollwithit.traspringbootregistration.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    Account findOneById(Long id);

    Account findByUsername(String username);

    Optional<Account> findByUsernameOrEmail(String username, String email);
}
