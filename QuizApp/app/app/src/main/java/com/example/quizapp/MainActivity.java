package com.example.quizapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

import java.util.Collections;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    QuestionBank obj = new QuestionBank();
    int index = 0;
    int ColorId;
    int QuestionId;
    QuestionFragment fragment_Obj;
    MaterialButton button_true, button_false;
    Boolean tag;
    String scoreAverage;
    int totalScore;
    ProgressBar progressBar;
    StorageManager storageObj = new StorageManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState !=null){
            index = savedInstanceState.getInt("QuestionIndex");

        }

        QuestionId = obj.questionArrayList.get(index).question;
        ColorId =obj.questionColors.get(index);
        UpdateFragment(QuestionId, ColorId);
        assignId(button_false, R.id.button_false);
        assignId(button_true, R.id.button_true);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setMax(100);
        progressBar.setProgress(0);
    }

    //1) findViewByID for each button
    //2) set on click listener for each button
    void assignId(MaterialButton btn, int id) {
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    public void UpdateFragment(int quesID, int colorID){
        FragmentManager manager = getSupportFragmentManager();
        manager.findFragmentById(R.id.question_container);
        fragment_Obj = QuestionFragment.newInstance(quesID, colorID);
        manager.beginTransaction().replace(R.id.question_container, fragment_Obj).commit();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("QuestionIndex", index);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()){
            case R.id.average: {
                String message = storageObj.GetData(MainActivity.this);
                int attemptCount = storageObj.CountNumberOfAttempts();
                int totalAverage = storageObj.CountAverageScore();
                System.out.println("Average Score = " + totalAverage);
                //bug when I try to switch to strings
                String dialogMessage = "Your correct answers are " + totalAverage
                        + " in " + attemptCount + " attempts !!";
//                String dialogMessage = R.string.your_correct_answers + "\t" + totalAverage
//                        + "\t" + R.string.in + "\t" + attemptCount + "\t" + R.string.attempts;
                System.out.println(dialogMessage);

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle(dialogMessage);
//                builder.setTitle(R.string.your_correct_answers + "\t" + totalAverage
//                        + "\t" + R.string.in + "\t" + attemptCount + "\t" + R.string.attempts);
                builder.setPositiveButton("OK", null);
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
                break;
            }

            case R.id.data_reset:{
                storageObj.ResetData(MainActivity.this);
                break;
            }
        }
        return true;
    }

    @Override
    public void onClick(View view) {
        MaterialButton btn = (MaterialButton)  view;
        if (index < obj.questionArrayList.size()-1) {
//when you click true
            if(btn.equals(button_true)){
                tag = true;
            } else {
                //when you click false

                tag = false;
            }
//correct answer
            if(tag==obj.questionArrayList.get(index).answer){
                totalScore++;
                Toast.makeText(this, R.string.CorrectAnswer, Toast.LENGTH_SHORT).show();
            }
            //wrong answer

            if(tag!=obj.questionArrayList.get(index).answer) {
                Toast.makeText(this, R.string.IncorrectAnswer, Toast.LENGTH_SHORT).show();
            }
//next question
            index++;
            QuestionId = obj.questionArrayList.get(index).question;
            ColorId = obj.questionColors.get(index);
            UpdateFragment(QuestionId, ColorId);
            progressBar.setProgress(progressBar.getProgress()+10);


        } else {
            //reveals score
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("Your Scores are " + "\t" + totalScore +"\t"+ " out of 10!!");
            scoreAverage = totalScore +"/" + 10 + "#";
            //save
            builder.setPositiveButton("Save", (dialogInterface, i) -> storageObj.SaveData
                    (MainActivity.this,scoreAverage));
            totalScore = 0;
            //ignore
            builder.setNegativeButton("Ignore",null);
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
            index = 0;
            //shuffle questions
            Collections.shuffle(obj.questionArrayList);
            //shuffle fragment colors
            Collections.shuffle(obj.questionColors);
            UpdateFragment(obj.questionArrayList.get(index).question,obj.questionColors.get(index));
            progressBar.setProgress(0);
        }

    }
}

