package com.example.IntI.controller;

import com.example.IntI.service.UserService;
import com.example.IntI.domain.User;
import com.example.IntI.domain.UserCreateForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/join")
    public String join(@ModelAttribute(value = "userCreateForm") UserCreateForm userCreateForm){
        userService.join(User.createUser(userCreateForm.getUserId()
                ,userCreateForm.getUserPassword(),userCreateForm.getNickName()));
        return "redirect:/login";
    }

    @GetMapping("/join")
    public String getJoin(Model model){
        model.addAttribute("userCreateForm",new UserCreateForm());
        return "join";
    }
}
