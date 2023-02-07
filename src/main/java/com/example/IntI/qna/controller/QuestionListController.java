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

        Member question1 = new Member(null, "도라에몽", "오전 12:03", "이거 어떻게 하는 건가요?");
        Member question2 = new Member("", "도라미", "오전 12:03", "제대로 되는 건지 모르겠어요");
        Member answer = new Member(null, "노진구", "오후 2:03", "답변인데용");
        Qna qna1 = new Qna(1, 1, question1, answer);
        Qna qna2 = new Qna(1, 1, question2, answer);

        List<Qna> questionData = new ArrayList<>();
        questionData.add(qna1);
        questionData.add(qna2);

        model.addAttribute("questionSummaryList", questionData);
        log.info("questionId={}", id);

        return "/qna-summary";
    }

    @GetMapping("/detail")
    public String qnaDetail(@RequestParam String questionId, Model model) {
        int id = Integer.parseInt(questionId);
        log.info("questionId={}", id);


        Member question = new Member(null, "도라에몽", "오전 12:03", "이거 어떻게 하는 건가요?");
        Member answer1 = new Member(null, "노진구", "오후 3:46", "답변인데용");
        Member answer2 = new Member("", "도라미", "오후 2:03", "잘 출력되는지 확인 중입니다.");

        List<Member> answerData = new ArrayList<>();
        answerData.add(answer1);
        answerData.add(answer2);

        model.addAttribute("question", question);
        model.addAttribute("answerList", answerData);
        log.info("questionId={}", id);

        return "/qna-detail";
    }

    @Data
    static class Qna {
        private int questid;
        private int roomid;
        private Member question;
        private Member answer;

        public Qna(int questid, int roomid, Member question, Member answer) {
            this.questid = questid;
            this.roomid = roomid;
            this.question = question;
            this.answer = answer;
        }
    }

    @Data
    static class Member {
        private String profile;
        private String name;
        private String time;
        private String context;

        public Member(String profile, String name, String time, String context) {
            this.profile = profile;
            this.name = name;
            this.time = time;
            this.context = context;
        }
    }
}
