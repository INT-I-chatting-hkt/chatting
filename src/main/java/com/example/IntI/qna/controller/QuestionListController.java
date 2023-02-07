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
    public String qnaSummary(@RequestParam Long roomId, Model model) {

        List<Question> questionList = qnaService.getAllQuestionsWithAdoptedAnswer(roomId);

        List<summaryQuestion> summaryQuestionList = new ArrayList<>();
        for(Question quest:questionList){
            summaryQuestion summaryQuest = new summaryQuestion(quest, quest.getAdoptedAnswer());
            summaryQuestionList.add(summaryQuest);
        }
        log.info("summaryQuestions={}", summaryQuestionList);

        model.addAttribute("QuestionList", summaryQuestionList);

        return "qna-summary";
    }

    @GetMapping("/detail")
    public String qnaDetailGet(@RequestParam Long questionId, Model model) {
        log.info("questionId={}", questionId);

        Question question = qnaService.getQuestion(questionId);
        Answer adoptAnswer = question.getAdoptedAnswer();
        List<Answer> answers = qnaService.getNotAdoptedAnswers(question.getId(),adoptAnswer.getId());
        
        model.addAttribute("question", question);
        model.addAttribute("answerList", answers);
        model.addAttribute("answerAdopt", adoptAnswer);

        return "qna-detail";
    }

    @ResponseBody
    @PostMapping("/detail")
    public Long qnaDetailPost(@RequestParam(value = "answerId") Long answerId,
                                  @RequestParam(value = "questionId") Long questionId) {
        log.info("answerId={}", answerId); // 채택된 답변의 id
        qnaService.adoptAnswer(answerId,questionId); // star를 클릭한 답변 채택
        return answerId;
    }

    public Long stringToLong(String id){
        Integer integerId = Integer.parseInt(id);
        return integerId.longValue();
    }
}
