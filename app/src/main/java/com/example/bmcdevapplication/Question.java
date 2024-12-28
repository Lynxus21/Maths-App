package com.example.bmcdevapplication;

import java.util.Random;

public class Question {

    private int firstNumber;
    private int secondNumber;
    private int answer;

    //Four answers with one being correct

    private int [] answerArray;

    //The button with the correct answer

    private int answerPosition;

    //Maximum value of firstNumber and SecondNumber

    private int upperLimit;

    //Question string

    private String questionPhrase;

    //Generate new question

    public Question(int upperLimit) {
        this.upperLimit = upperLimit;
        Random randomNumberMaker = new Random();

        int min = 0;
        int max = this.upperLimit;

        Random r = new Random();
        int i1 = r.nextInt(max - min + 1) + min;

        this.firstNumber = randomNumberMaker.nextInt(max) + min;
        this.secondNumber = randomNumberMaker.nextInt(max) + min;

        int rn = randomNumberMaker.nextInt(4);
        switch (rn) {

            case 1:
                this.answer = this.firstNumber + this.secondNumber;
                this.questionPhrase = firstNumber + " + " + secondNumber + " = ";
                break;

            case 2:
                this.answer = this.firstNumber - this.secondNumber;
                this.questionPhrase = firstNumber + " - " + secondNumber + " = ";
                break;

            case 3:
                this.answer = this.firstNumber * this.secondNumber;
                this.questionPhrase = firstNumber + " * " + secondNumber + " = ";
                break;

            case 4:
                this.answer = this.firstNumber / this.secondNumber;
                this.questionPhrase = firstNumber + " / " + secondNumber + " = ";
                break;
        }

        this.answerPosition = randomNumberMaker.nextInt(4);
        this.answerArray = new int[]{0, 1, 2, 3};

        this.answerArray[0] = answer + 6;
        this.answerArray[1] = answer - 5;
        this.answerArray[2] = answer + 7;
        this.answerArray[3] = answer - 5;

        this.answerArray = shuffleArray(this.answerArray);

        answerArray[answerPosition] = answer;
    }

    private int [] shuffleArray(int[] array) {
        int index, temp;
        Random randomNumberGenerator = new Random();

        for (int i = array.length - 1; i > 0; i--) {
            index = randomNumberGenerator.nextInt(i + 1);
            temp = array[index];
            array[index] = array[i];
            array[i] = temp;
        }
        return array;
    }

    public int getFirstNumber() {
        return firstNumber;
    }

    public void setFirstNumber(int firstNumber) {
        this.firstNumber = firstNumber;
    }

    public int getSecondNumber() {
        return secondNumber;
    }

    public void setSecondNumber(int secondNumber) {
        this.secondNumber = secondNumber;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    public int[] getAnswerArray() {
        return answerArray;
    }

    public void setAnswerArray(int[] answerArray) {
        this.answerArray = answerArray;
    }

    public int getAnswerPosition() {
        return answerPosition;
    }

    public void setAnswerPosition(int answerPosition) {
        this.answerPosition = answerPosition;
    }

    public int getUpperLimit() {
        return upperLimit;
    }

    public void setUpperLimit(int upperLimit) {
        this.upperLimit = upperLimit;
    }

    public String getQuestionPhrase() {
        return questionPhrase;
    }

    public void setQuestionPhrase(String questionPhrase) {
        this.questionPhrase = questionPhrase;
    }
}