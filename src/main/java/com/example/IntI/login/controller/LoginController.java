package com.example.IntI.login.controller;

import com.example.IntI.service.UserService;
import com.example.IntI.domain.User;
import com.example.IntI.login.domain.LoginForm;
import com.example.IntI.security.JwtTokenProvider;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class LoginController {
    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    @GetMapping("/login")
    public String loginForm(Model model){
        model.addAttribute("loginForm",new LoginForm());
        return "login2";
    }
    @PostMapping("/login")
    public String login(@ModelAttribute(value = "loginForm")LoginForm loginForm, HttpServletResponse response){
        User user = userService.findOneByUserId(loginForm.getUserId());
        if(user.validate(loginForm.getUserId(),loginForm.getUserPassword())){
            String token = jwtTokenProvider.createAccessToken(user.getUserId());
            Cookie cookie = new Cookie("inti-token", token);
            cookie.setMaxAge(300);
            cookie.setPath("/");
            response.addCookie(cookie);
        }
        return "redirect:chat";
    }
}
