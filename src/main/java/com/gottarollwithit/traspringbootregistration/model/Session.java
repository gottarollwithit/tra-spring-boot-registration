package com.gottarollwithit.traspringbootregistration.model;

import lombok.Getter;
import org.springframework.security.core.authority.AuthorityUtils;

@Getter
public class Session extends org.springframework.security.core.userdetails.User {

    private Account account;

    public Session(Account account) {
        super(account.getUsername(), account.getPassword(), AuthorityUtils.createAuthorityList("USER"));
        this.account = account;
    }

}
