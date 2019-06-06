package com.student.crecer.controller;

import com.student.crecer.domain.Role;
import com.student.crecer.domain.User;
import com.student.crecer.repositories.UserRepo;
import com.student.crecer.servise.UserServise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationController {

    @Autowired
    private UserServise userServise;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(@Valid User user, BindingResult bindingResult, Model model) {
        if (user.getPassword() != null && !user.getPassword().equals(user.getPassword2())) {
            model.addAttribute("passwordError", "Passwords are not equals!");
        }
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = ControllerUtils.getErrors(bindingResult);

            model.mergeAttributes(errors);
            return "registration";
        }
        if (!userServise.addUser(user)) {
            model.addAttribute("usernameError", "User is exists!!");
            return "registration";
        }

        return "redirect:/login";
    }
}
