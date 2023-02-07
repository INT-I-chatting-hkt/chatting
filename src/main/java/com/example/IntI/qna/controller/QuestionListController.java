package com.example.IntI.qna.controller;

import com.example.IntI.chat.domain.Question;
import com.example.IntI.chat.service.ChattingRoomService;
import com.example.IntI.domain.Answer;
import com.example.IntI.domain.User;
import com.example.IntI.qna.domain.summaryQuestionDto;
import com.example.IntI.qna.service.QnaService;
import com.example.IntI.security.JwtTokenProvider;
import com.example.IntI.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Data;
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
    private final JwtTokenProvider jwtTokenProvider;

    private final ChattingRoomService chattingRoomService;

    @GetMapping
    public String qnaSummary(@RequestParam Long roomId, Model model) {

        List<Question> questionList = qnaService.getAllQuestionsWithAdoptedAnswer(roomId);

        List<summaryQuestionDto> summaryQuestionList = new ArrayList<>();
        for(Question quest:questionList){
            summaryQuestionDto summaryQuest = new summaryQuestionDto(quest, quest.getAdoptedAnswer());
            summaryQuestionList.add(summaryQuest);
        }
        log.info("summaryQuestions={}", summaryQuestionList);

        model.addAttribute("QuestionList", summaryQuestionList);
        model.addAttribute("roomName",chattingRoomService.findOne(roomId).getName());

        return "qna-summary";
    }

    @GetMapping("/detail")
    public String qnaDetailGet(@RequestParam Long questionId, Model model,HttpServletRequest request) {
        log.info("questionId={}", questionId);

        String token = jwtTokenProvider.resolveCookieToken(request,"inti-token");
        String userId = jwtTokenProvider.getValidateValue(token);

        Question question = qnaService.getQuestion(questionId);
        String questionUserId = question.getUser().getUserId();
        Answer adoptAnswer = question.getAdoptedAnswer();
        Long adoptAnswerId = (adoptAnswer != null ? adoptAnswer.getId() : null );
        List<Answer> answers = qnaService.getNotAdoptedAnswers(question.getId(), adoptAnswerId);
        
        model.addAttribute("question", question);
        model.addAttribute("answerList", answers);
        model.addAttribute("answerAdopt", adoptAnswer);
        /**
         * userId : 접속하고 있는 유저의 Id, questionUserId : 질문한 유저의 아이디
         */
        model.addAttribute("userId",userId);
        model.addAttribute("questionUserId",questionUserId);

        return "qna-detail";
    }

    @ResponseBody
    @PostMapping("/adopt")
    public Long qnaDetailPost(@RequestBody AdoptForm adaptForm) {
        if(!adaptForm.getUserId().equals(adaptForm.getQuestionUserId())){
            return adaptForm.getQuestionId();
        }
        log.info("answerId={}", adaptForm.getAnswerId()); // 채택된 답변의 id
        qnaService.adoptAnswer(adaptForm.getAnswerId(), adaptForm.getQuestionId()); // star를 클릭한 답변 채택
        return adaptForm.getQuestionId();
    }

    @ResponseBody
    @PostMapping("/answer")
    public Long createAnswer(@RequestBody AnswerForm answerForm, HttpServletRequest request){
        log.info("answerForm={}", answerForm);
        String token = jwtTokenProvider.resolveCookieToken(request,"inti-token");
        String userId = jwtTokenProvider.getValidateValue(token);
        return qnaService.createAnswer(userId,answerForm.getContext(),answerForm.getQuestionId());
    }

    @Data
    public static class AdoptForm{
        private Long answerId;
        private Long questionId;
        private String userId;
        private String questionUserId;
    };

    @Data
    public static class AnswerForm{
        private String context;
        private Long questionId;
    }


}
