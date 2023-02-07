package com.example.IntI.qna.controller;

import com.example.IntI.chat.domain.Question;
import com.example.IntI.domain.Answer;
import com.example.IntI.qna.domain.summaryQuestion;
import com.example.IntI.qna.service.QnaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequestMapping(value = "/qna")
@RequiredArgsConstructor
public class QuestionListController {
    private final QnaService qnaService;

    @GetMapping
    public String qnaSummary(@RequestParam String roomId, Model model) {
        Long room = stringToLong(roomId);

        List<Question> questionList = qnaService.getAllQuestions(room); // 해당 채팅방의 모든 question을 get

        List<summaryQuestion> summaryQuestionList = new ArrayList<>();
        for(Question quest:questionList){
            summaryQuestion summaryQuest = new summaryQuestion(quest, qnaService.getAdoptAnswer(quest.getId()));
            summaryQuestionList.add(summaryQuest);
        }

        log.info("questionId={}", room);
        log.info("summaryQuestions={}", summaryQuestionList);

        model.addAttribute("QuestionList", summaryQuestionList);

        return "qna-summary";
    }

    @GetMapping("/detail")
    public String qnaDetailGet(@RequestParam String questionId, Model model) {

        Long questId = stringToLong(questionId);
        log.info("questionId={}", questId);

        Question question = qnaService.getQuestion(questId);
        List<Answer> answerData = qnaService.getNotAdoptedAnswers(questId);
        Answer adoptAnswer = qnaService.getAdoptAnswer(questId);
        
        model.addAttribute("question", question);
        model.addAttribute("answerList", answerData);
        model.addAttribute("answerAdopt", adoptAnswer);

        return "qna-detail";
    }

    @PostMapping("/detail")
    public String qnaDetailPost(@RequestParam String questionId, @RequestBody String answerId, Model model) {

        Long questId = stringToLong(questionId);
        Long ansId = stringToLong(answerId);
        log.info("answerId={}", ansId); // 채택된 답변의 id 
        log.info("questionId={}", questId);

        qnaService.adoptAnswer(ansId); // star를 클릭한 답변 채택
        Question question = qnaService.getQuestion(questId);
        List<Answer> answerData = qnaService.getNotAdoptedAnswers(questId);
        Answer adoptAnswer = qnaService.getAdoptAnswer(questId);

        model.addAttribute("question", question);
        model.addAttribute("answerList", answerData);
        model.addAttribute("answerAdopt", adoptAnswer);

        return "redirect:/qna-detail";
    }

    public Long stringToLong(String id){
        Integer integerId = Integer.parseInt(id);
        return integerId.longValue();
    }
}
