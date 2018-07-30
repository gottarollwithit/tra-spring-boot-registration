package com.gottarollwithit.traspringbootregistration.form;

import com.gottarollwithit.traspringbootregistration.helper.Match;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Match(first = "password", second = "passwordRepeated", message = "Passwords should match")
@Getter
@Setter
public class RegistrationForm {

    @Email(message = "Not a valid e-mail adress")
    private String email = "";

    @Size(min = 3, max = 20, message = "Username should be between 3-20 characters")
    private String username = "";

    @Pattern(regexp = "(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{7,}", message = "Password needs to be at least 7 characters including at least 1 number and 1 upper case letter")
    private String password = "";

    private String passwordRepeated = "";

    @Size(min = 3, max = 20, message = "First name should be between 3-20 characters")
    private String firstName;

    @Size(min = 3, max = 20, message = "Last name should be between 3-20 characters")
    private String lastName;

    @NotNull(message = "Please enter your date of birth")
    @Past(message = "Date must be in past ")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateOfBirth;

}
