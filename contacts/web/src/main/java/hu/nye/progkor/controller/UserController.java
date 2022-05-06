package hu.nye.progkor.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * User Controller.
 */
@Controller
public class UserController {

    /**
     * Registration page.
     */
    @GetMapping(path = "/view/registration.html")
    public String registrationPage(final Model model) {
        return "view/registration";
    }
}
