package com.example.IntI.qna.domain;

import com.example.IntI.chat.domain.Question;
import com.example.IntI.domain.Answer;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Optional;

@Data
public class QnaDto {

    // 질문 정보
    private String questName;
    private String questImagePath;
    private String questTime;
    private String questContext;

    // 채택된 답변 정보
    private String answerName;
    private String answerImagePath;
    private String answerTime;
    private String answerContext;

    public QnaDto createQnaDto(Question question, Optional<Answer> answer) {

        questName = question.getUser().getNickname();
        questImagePath = question.getUser().getProfileUrl();
        LocalDateTime qTime = question.getCreateTime();
        questTime = qTime.getYear() + "-" + qTime.getMonth() + "-" + qTime.getDayOfMonth() + " " + qTime.getHour() + ":" + qTime.getMinute();
        questContext = question.getContext();

        answerName = answer.get().getUser().getNickname();
        questImagePath = answer.get().getUser().getProfileUrl();
        LocalDateTime aTime = answer.get().getCreateTime();
        questTime = aTime.getYear() + "-" + aTime.getMonth() + "-" + aTime.getDayOfMonth() + " " + aTime.getHour() + ":" + aTime.getMinute();
        questContext = answer.get().getContext();

        return this;
    }
}