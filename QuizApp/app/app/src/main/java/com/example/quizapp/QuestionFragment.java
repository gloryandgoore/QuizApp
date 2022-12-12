package com.example.quizapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class QuestionFragment extends Fragment {

    int color;
    int question;
    TextView questionTV;

    public static QuestionFragment newInstance(int questionID, int colorID){
        Bundle args = new Bundle();
        args.putInt("QuestionId", questionID);
        args.putInt("ColorId", colorID);
        QuestionFragment fragment = new QuestionFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_question, container, false);
        questionTV = (TextView) view.findViewById(R.id.question_TV);
        question = getArguments().getInt("QuestionId");
        color = getArguments().getInt("ColorId");
        questionTV.setText(question);
        questionTV.setBackgroundResource(color);
        return view;
    }
}
