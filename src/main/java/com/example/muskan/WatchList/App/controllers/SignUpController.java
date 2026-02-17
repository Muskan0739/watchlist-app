package com.example.muskan.WatchList.App.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.example.muskan.WatchList.App.entity.UserInfo;
import com.example.muskan.WatchList.App.services.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class SignUpController {

    @Autowired
    UserService userService;

    @Autowired
    AuthenticationManager authenticationManager;

    @GetMapping("/signup")
    public ModelAndView showSignUpPage() {
        Map<String, Object> model = new HashMap<>();
        model.put("signUp", new UserInfo());
        return new ModelAndView("signupForm", model);
    }

    @PostMapping("/signup")
    public ModelAndView submitSignupForm(@ModelAttribute("signUp") UserInfo userinfo,
                                         BindingResult result,
                                         HttpServletRequest request,
                                         HttpServletResponse response) {

        if (result.hasErrors()) {
            return new ModelAndView("signupForm");
        }

        // Capture raw password before anything touches it
        String rawPassword = userinfo.getPassword();

        if (userService.emailExists(userinfo.getEmail())) {
            // Email exists — just authenticate them
            try {
                UsernamePasswordAuthenticationToken authToken =
                    new UsernamePasswordAuthenticationToken(userinfo.getEmail(), rawPassword);

                Authentication authentication = authenticationManager.authenticate(authToken);
                SecurityContextHolder.getContext().setAuthentication(authentication);

                new HttpSessionSecurityContextRepository()
                    .saveContext(SecurityContextHolder.getContext(), request, response);

                return new ModelAndView("redirect:/");

            } catch (Exception e) {
                // Wrong password
                ModelAndView mv = new ModelAndView("signupForm");
                mv.addObject("error", "Email already registered. Check your password.");
                mv.addObject("signUp", new UserInfo());
                return mv;
            }

        } else {
            // New user — create then authenticate
            userService.createUser(userinfo);  // encodes password inside here

            UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(userinfo.getEmail(), rawPassword);

            Authentication authentication = authenticationManager.authenticate(authToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);

            new HttpSessionSecurityContextRepository()
                .saveContext(SecurityContextHolder.getContext(), request, response);

            return new ModelAndView("redirect:/");
        }
    }
}