package com.example.bmcdevapplication;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private List<Question> questions;
    private int answerCorrect;
    private int answerIncorrect;

    public int getThisQuestion() {
        return thisQuestion;
    }

    public void setThisQuestion(int thisQuestion) {
        this.thisQuestion = thisQuestion;
    }

    public int getMaxQuestions() {
        return maxQuestions;
    }

    public void setMaxQuestions(int maxQuestions) {
        this.maxQuestions = maxQuestions;
    }

    private int thisQuestion;
    private int maxQuestions = 10;

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public int getAnswerCorrect() {
        return answerCorrect;
    }

    public void setAnswerCorrect(int answerCorrect) {
        this.answerCorrect = answerCorrect;
    }

    public int getAnswerIncorrect() {
        return answerIncorrect;
    }

    public void setAnswerIncorrect(int answerIncorrect) {
        this.answerIncorrect = answerIncorrect;
    }

    public int getTotalQuestions() {
        return totalQuestions;
    }

    public void setTotalQuestions(int totalQuestions) {
        this.totalQuestions = totalQuestions;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Question getCurrentQuestion() {
        return currentQuestion;
    }

    private int totalQuestions;
    private int score;
    private Question currentQuestion;

    public Game() {
        answerCorrect = 0;
        answerIncorrect = 0;
        totalQuestions = 0;
        questions = new ArrayList<Question>();

    }

    public void makeNewQuestion() {
        currentQuestion = new Question(12);
        totalQuestions++;
        questions.add(currentQuestion);
    }

    public boolean checkAnswer(int submittedAnswer) {
        boolean isCorrect;
        if (currentQuestion.getAnswer() == submittedAnswer) {
            isCorrect=true;
            answerCorrect++;
            score = score + 10;

        } else {

            isCorrect=false;
            answerIncorrect++;
            score = score - 5;
        }
        return isCorrect;
    }
}