package com.example.IntI.qna.domain;

import lombok.Data;

@Data
public class QnaDto {

    // 질문 정보
    private String questName;
    private String questImage;
    private String questTime;
    private String questContext;

    // 채택된 답변 정보
    private String answerName;
    private String answerImage;
    private String answerTime;
    private String answerContext;

    public QnaDto(String questName, String questImage, String questTime, String questContext) {
        this.questName = questName;
        this.questImage = questImage;
        this.questTime = questTime;
        this.questContext = questContext;
    }
}