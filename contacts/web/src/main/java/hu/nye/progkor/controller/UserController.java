package hu.nye.progkor.controller;

import hu.nye.progkor.UserService;
import hu.nye.progkor.exception.NotFoundException;
import hu.nye.progkor.exception.WrongPasswordOrUsername;
import hu.nye.progkor.model.UserDTO;
import hu.nye.progkor.model.request.ContactRequest;
import hu.nye.progkor.model.request.UserRequest;
import hu.nye.progkor.model.response.ContactResponse;
import hu.nye.progkor.model.response.UserResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * User Controller.
 */
@RequiredArgsConstructor
@ControllerAdvice
@Controller
@Slf4j
public class UserController {

    private final UserService userService;
    private final Converter<UserDTO, UserResponse> dataToResponse;
    private final Converter<UserRequest, UserDTO> requestToDto;

    private static boolean login = false;

    /**
     * Get login status.
     */
    public static boolean isLogin() {
        return login;
    }

    /**
     * Edit contact with POST method.
     */
    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String editContactPage(final RedirectAttributes redirectAttributes,
                                  final Model model,
                                  final @RequestParam(value = "email", required = false) String email,
                                  final @RequestParam(value = "password", required = false) String password,
                                  final UserRequest userRequest
    ) {
        login = userService.LoginToTheSite(requestToDto.convert(userRequest));
        log.info(String.valueOf(login));
        redirectAttributes.addFlashAttribute("loginBar", login);
        return "redirect:/";
    }


    /**
     * Registration page.
     */
    @GetMapping(path = "/view/registration.html")
    public String registrationPage(final Model model) {
        return "view/registration";
    }


    /**
     * View page.
     */
    @GetMapping(path = "/")
    public String indexLogined(final Model model) {
        model.addAttribute("loginBar", login);
        return "/index";
    }

}
