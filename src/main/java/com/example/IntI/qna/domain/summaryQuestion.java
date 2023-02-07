package com.example.IntI.qna.domain;
import com.example.IntI.chat.domain.Question;
import com.example.IntI.domain.Answer;
import lombok.Data;

@Data
public class summaryQuestion {

    private Question question;
    private Answer answer;

    public summaryQuestion(Question question, Answer answer) {
        this.question = question;
        this.answer = answer;
    }
}