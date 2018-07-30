package com.gottarollwithit.traspringbootregistration.controller;

import com.gottarollwithit.traspringbootregistration.model.Account;
import com.gottarollwithit.traspringbootregistration.model.Session;
import com.gottarollwithit.traspringbootregistration.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class ProfileController {

    @Autowired
    private AccountService userService;

    @RequestMapping(value = {"/", "/profile"})
    public String getHomePage(Model model) {
        log.debug("Getting home page");

        Session session = (Session) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Account account = userService.findOneById(session.getAccount().getId());
        model.addAttribute("profile", account);

        return "/profile";
    }

}
