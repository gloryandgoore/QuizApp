package com.example.quizapp;

import java.util.ArrayList;

public class QuestionBank {
    ArrayList<Integer> questionColors = new ArrayList<>();
    ArrayList<Question> questionArrayList = new ArrayList<>();

    QuestionBank(){

        //colors for question background
        questionColors.add(R.color.Magenta);
        questionColors.add(R.color.Violet);
        questionColors.add(R.color.Plum);
        questionColors.add(R.color.PaleVioletRed);
        questionColors.add(R.color.PapayaWhip);
        questionColors.add(R.color.Chocolate);
        questionColors.add(R.color.GreenYellow);
        questionColors.add(R.color.DarkSeaGreen);
        questionColors.add(R.color.SlateBlue);
        questionColors.add(R.color.DodgerBlue);

        //questions/answers

        Question question1 = new Question(R.string.Question1,true);
        Question question2 = new Question(R.string.Question2,true);
        Question question3 = new Question(R.string.Question3,false);
        Question question4 = new Question(R.string.Question4,false);
        Question question5 = new Question(R.string.Question5,false);
        Question question6 = new Question(R.string.Question6,true);
        Question question7 = new Question(R.string.Question7,false);
        Question question8 = new Question(R.string.Question8,true);
        Question question9 = new Question(R.string.Question9,true);
        Question question10 = new Question(R.string.Question10,false);

        //adding questions to array list
        questionArrayList.add(question1);
        questionArrayList.add(question2);
        questionArrayList.add(question3);
        questionArrayList.add(question4);
        questionArrayList.add(question5);
        questionArrayList.add(question6);
        questionArrayList.add(question7);
        questionArrayList.add(question8);
        questionArrayList.add(question9);
        questionArrayList.add(question10);

    }

}
