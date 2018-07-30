package com.gottarollwithit.traspringbootregistration.controller;

import com.gottarollwithit.traspringbootregistration.form.RegistrationForm;
import com.gottarollwithit.traspringbootregistration.helper.StaticURLs;
import com.gottarollwithit.traspringbootregistration.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Slf4j
@Controller
public class RegistrationController {

    @Autowired
    private AccountService userService;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("registrationForm", new RegistrationForm());
        log.debug("Getting registration page");
        return StaticURLs.REGISTRATION_URL;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registration(@Valid @ModelAttribute("registrationForm") RegistrationForm registrationForm, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return StaticURLs.REGISTRATION_URL;
        }

        if (userService.getUserByUsernameOrEmail(registrationForm.getUsername(), registrationForm.getEmail()).isPresent()) {
            log.warn("User exists with same e-mail or username");
            bindingResult.reject("emailOrUsername.exists", "User exists with same e-mail or username");
            return StaticURLs.REGISTRATION_URL;
        }

        userService.create(registrationForm);
        return "redirect:" + StaticURLs.LOGIN_URL;
    }

}
