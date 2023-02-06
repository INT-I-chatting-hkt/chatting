package com.example.IntI.qna.controller;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping(value = "/qna")
public class QuestionListController {

    @GetMapping
    public String qnaSummary(@RequestParam String roomId, Model model) {
        int id = Integer.parseInt(roomId);
        model.addAttribute("roomId", id);
        log.info("roomId={}", id);

        Qna qnaData1 = new Qna("도라에몽", "오전 12:03", "이거 어떻게 하는 건가요?");
        Qna qnaData2 = new Qna("노진구", "오후 2:03", "답변인데용");

        List<Qna> data = new ArrayList<>();
        data.add(qnaData1);
        data.add(qnaData2);

        model.addAttribute("user", qnaData1);
        model.addAttribute("data", "Hi");
        log.info("questionId={}", id);

        System.out.println(data.get(0));
        System.out.println(data.get(1));

        return "/qna-summary";
    }

    @GetMapping("/detail")
    public String qnaDetail(@RequestParam String questionId, Model model) {
        int id = Integer.parseInt(questionId);
        log.info("questionId={}", id);

        return "/qna-detail";
    }

    @Data
    static class Qna {
        private String questname;
        private String questtime;
        private String questcontext;

        public Qna(String questname, String questtime, String questcontext) {
            this.questname = questname;
            this.questtime = questtime;
            this.questcontext = questcontext;
        }
    }
}
