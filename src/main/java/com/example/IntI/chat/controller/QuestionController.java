package com.example.IntI.chat.controller;

import com.example.IntI.chat.domain.Question;
import com.example.IntI.chat.service.QuestionService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;

    @GetMapping(value = "/questions")
    @ResponseBody
    public List<Question> findAll(@RequestParam(value = "roomId")Long roomId){
        return questionService.findAll(roomId);
    }
}
