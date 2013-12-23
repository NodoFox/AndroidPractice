package com.nodovitt.geoquiz;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class QuizMainActivity extends Activity {

    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    private TextView mQuestionTextView;
    private Button cheatButton;
    private boolean isCheated;
    private static final String TAG = "QuizMainActivity";
    private static final String INDEX = "mCurrentIndex";
    public static final String IS_ANSWER_TRUE = "is_answer_true";

    private TrueFalse[] mQuestionBank = new TrueFalse[] {
        new TrueFalse(R.string.question_africa, false),
        new TrueFalse(R.string.question_americas, true),
        new TrueFalse(R.string.question_asia, true),
        new TrueFalse(R.string.question_oceans, true) };
    private int mCurrentIndex = 0;

    private void updateQuestion() {
        int question = mQuestionBank[mCurrentIndex].getQuestion();
        mQuestionTextView.setText(question);
    }

    @TargetApi(11)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_main);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB){
            ActionBar a = getActionBar();
            a.setSubtitle("Water Bodies");
        }
        // updating from the previous state
        if (savedInstanceState != null) {
            mCurrentIndex = savedInstanceState.getInt(INDEX);
            isCheated = savedInstanceState.getBoolean(CheatActivity.IS_CHEATED);
        }
        Log.d(TAG, "onCreate(Bundle savedInstanceState) called");
        // getting the view and setting the question on the view, once.
        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);
        updateQuestion();

        cheatButton = (Button) findViewById(R.id.cheatButton);

        cheatButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                // Start Cheat Activity
                Intent i = new Intent(QuizMainActivity.this, CheatActivity.class);
                boolean answer = mQuestionBank[mCurrentIndex].isTrueQuestion();
                i.putExtra(IS_ANSWER_TRUE, answer);
                startActivityForResult(i, 0);

            }
        });
        mTrueButton = (Button) findViewById(R.id.true_button);
        // Listener
        mTrueButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (mQuestionBank[mCurrentIndex].isTrueQuestion())
                    checkAnswer(true);
                else {
                    checkAnswer(false);
                }
            }
        });

        mFalseButton = (Button) findViewById(R.id.false_button);
        // Setting listener
        mFalseButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (mQuestionBank[mCurrentIndex].isTrueQuestion() == false)
                    checkAnswer(true);
                else {
                    checkAnswer(false);
                }
            }
        });

        mNextButton = (Button) findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                isCheated = false;
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                updateQuestion();

            }
        });
    }

    public void checkAnswer(boolean answer){
        int messageResId = 0;

        if (isCheated) {
            messageResId = R.string.judgement_toast;
        } else {
            if (answer == true) {
                messageResId = R.string.correct_toast;
            } else {
                messageResId = R.string.incorrect_toast;
            }
        }

        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT)
            .show();
    }

    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data ){
        if(data == null){
            return;
        }
        isCheated = data.getBooleanExtra(CheatActivity.IS_CHEATED, false);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.quiz_main, menu);
        return true;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(INDEX, mCurrentIndex);
        outState.putBoolean(CheatActivity.IS_CHEATED, isCheated);
        Log.d(TAG, "onSaveInstanceState() called");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart() called");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause() called");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() called");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop() called");
    }
}