package com.telerikacademy.web.sportforumgroup10.controllers.mvc;

import com.telerikacademy.web.sportforumgroup10.exceptions.AuthorizationException;
import com.telerikacademy.web.sportforumgroup10.helpers.AuthenticationHelper;
import com.telerikacademy.web.sportforumgroup10.helpers.UserMapper;
import com.telerikacademy.web.sportforumgroup10.models.Dto.LoginDto;
import com.telerikacademy.web.sportforumgroup10.models.Dto.RegisterDto;
import com.telerikacademy.web.sportforumgroup10.services.Contracts.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class AuthenticationMvcController {
    private final UserService userService;
    private final AuthenticationHelper authenticationHelper;

    private final UserMapper userMapper;

    public AuthenticationMvcController(UserService userService, AuthenticationHelper authenticationHelper, UserMapper userMapper) {
        this.userService = userService;
        this.authenticationHelper = authenticationHelper;
        this.userMapper = userMapper;
    }

    @GetMapping("/login")
    public String showLoginPage(Model model) {
        model.addAttribute("login", new LoginDto());
        return "LoginView";
    }

    @PostMapping("/login")
    public String handleLogin(@Valid @ModelAttribute("login") LoginDto loginDto,
                              BindingResult bindingResult,
                              HttpSession session) {
        if (bindingResult.hasErrors()) {
            return "LoginView";
        }
        try {
            authenticationHelper.tryAuthenticateUser(loginDto);
            session.setAttribute("currentUser", loginDto.getUsername());
            return "redirect:/";
            //ToDo should be authentication exception
        } catch (AuthorizationException e) {
            bindingResult.rejectValue("username", "auth_error", e.getMessage());
            return "LoginView";
        }
    }

    @GetMapping("/logout")
    public String handleLogout(HttpSession session) {
        session.removeAttribute("currentUser");
        return "redirect:/";
    }

    @GetMapping("/register")
    public String showRegisterPage(Model model) {
        model.addAttribute("register", new RegisterDto());
        return "RegisterView";
    }

    @PostMapping("/register")
    public String handleRegister(@Valid @ModelAttribute("register") RegisterDto registerDto,
                                 BindingResult bindingResult,
                                 HttpSession session) {
        if (bindingResult.hasErrors()) {
            return "RegisterView";
        }
        try {

        } catch ()
    }
}
