package com.nodovitt.geoquiz;

import java.util.Locale;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends Activity {

    private Button showAnswer;
    private Boolean answer;
    private TextView answerTV;
    private boolean isAnswerShown = false;
    public static final String IS_CHEATED= "is_cheated";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);
        
        showAnswer = (Button) findViewById(R.id.showAnswerButton);
        answer = getIntent().getBooleanExtra(QuizMainActivity.IS_ANSWER_TRUE, false);
        answerTV = (TextView) findViewById(R.id.answerTextView);
        showAnswer.setOnClickListener(new View.OnClickListener() {
            
            @Override
            public void onClick(View v) {
                isAnswerShown = true;
                setAnswerShown(isAnswerShown);
                answerTV.setText(answer+"".toUpperCase(Locale.US));
            }
        });
        
        // if not cheated
        setAnswerShown(isAnswerShown);
    }
    
    public void setAnswerShown(boolean isAnswerShown){
        Intent i = new Intent();
        i.putExtra(IS_CHEATED, isAnswerShown);
        setResult(Activity.RESULT_OK,i);
    }
    @Override
    protected void onPause(){
        super.onPause();
        
    }
}
