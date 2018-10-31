package com.example.keren.multiplechoicequiz;

public class Flashcards {
    private String questionF;
    private String answerFTitle;
    private String answerF;

    public Flashcards(String questionF, String answerF) {
        this.questionF = questionF;
        this.answerF = answerF;
        this.answerFTitle = answerFTitle;
    }

    Flashcards(){

    }

    public String getQuestionF() {
        return questionF;
    }

    public void setQuestionF(String questionF) {
        this.questionF = questionF;
    }

    public String getAnswerF() {
        return answerF;
    }

    public void setAnswerF(String answerF) {
        this.answerF = answerF;
    }

    public String getAnswerFTitle() { return answerFTitle; }

    public void setAnswerFTitle(String answerFTitle) {this.answerFTitle=answerFTitle;}

}
