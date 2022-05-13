package hu.nye.progkor.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ExceptionController {


    /**
     * Not found get.
     */
    @GetMapping(path = "/exception/404.html")
    public String notFoundPage(final Model model) {
        model.addAttribute("loginBar", UserController.isLogin());
        return "exception/404";
    }

    /**
     * Not found get.
     */
    @GetMapping(path = "/exception/404")
    public String notFoundPage2(final Model model) {
        model.addAttribute("loginBar", UserController.isLogin());
        return "exception/404";
    }

    /**
     * Id modify get.
     */
    @GetMapping(path = "/exception/nicetry.html")
    public String niceTry(final Model model) {
        model.addAttribute("loginBar", UserController.isLogin());
        return "exception/nicetry";
    }

    /**
     * Id modify get.
     */
    @GetMapping(path = "/exception/nicetry")
    public String niceTry2(final Model model) {
        model.addAttribute("loginBar", UserController.isLogin());
        return "exception/nicetry";
    }
}
