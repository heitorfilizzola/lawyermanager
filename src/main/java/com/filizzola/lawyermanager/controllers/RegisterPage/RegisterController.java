package com.filizzola.lawyermanager.controllers.RegisterPage;

import com.filizzola.lawyermanager.dto.RegisterRequestDTO;
import com.filizzola.lawyermanager.models.Roles;
import com.filizzola.lawyermanager.models.User;
import com.filizzola.lawyermanager.repositories.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;


@Controller
public class RegisterController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("register")
    public ModelAndView showRegisterPage() {
        ModelAndView mv = new ModelAndView("RegisterPage/index");
        mv.addObject("registerRequestDTO", new RegisterRequestDTO());
        return mv;
    }

    @PostMapping("register")
    public ModelAndView register(@Valid @ModelAttribute("registerRequestDTO") RegisterRequestDTO dto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("RegisterPage/index");
        }

        userRepository.findFirstByUsername(dto.getUsername()).ifPresent(u -> {
            bindingResult.rejectValue("username", "error.username", "Username already exists");
        });

        userRepository.findFirstByEmail(dto.getEmail()).ifPresent(u -> {
            bindingResult.rejectValue("email", "error.username", "Username already exists");
        });

        if (bindingResult.hasErrors()) {
            return new ModelAndView("RegisterPage/index", "registerRequestDTO", dto);
        }

        User user = new User();
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setRoles(Roles.user);
        user.setAccountCreation(LocalDateTime.now());

        userRepository.save(user);

        return new ModelAndView("redirect:/login");
    }
}
