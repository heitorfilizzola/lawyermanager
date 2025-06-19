package com.filizzola.lawyermanager.controllers.LoginPage;

import com.filizzola.lawyermanager.dto.LoginRequestDTO;
import com.filizzola.lawyermanager.repositories.UserRepository;
import com.filizzola.lawyermanager.models.User;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("login")
    public ModelAndView showLoginPage() {
        ModelAndView mv = new ModelAndView("LoginPage/index");
        mv.addObject("loginRequestDTO", new LoginRequestDTO());
        return mv;
    }


}
