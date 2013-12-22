package com.nodovitt.geoquiz;

import android.app.Activity;
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
	private static final String TAG = "QuizMainActivity";
	private static final String INDEX = "mCurrentIndex";

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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_quiz_main);
		
		// updating from the previous state
		if(savedInstanceState!=null){
			mCurrentIndex = savedInstanceState.getInt(INDEX);
		}
		Log.d(TAG,"onCreate(Bundle savedInstanceState) called");
		// getting the view and setting the question on the view, once.
		mQuestionTextView = (TextView) findViewById(R.id.question_text_view);
		updateQuestion();

		cheatButton = (Button) findViewById(R.id.cheatButton);
		
		cheatButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				// Start Cheat Activity
				
			}
		});
		mTrueButton = (Button) findViewById(R.id.true_button);
		// Listener
		mTrueButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (mQuestionBank[mCurrentIndex].isTrueQuestion())
					Toast.makeText(QuizMainActivity.this,
							R.string.correct_toast, Toast.LENGTH_SHORT).show();
				else {
					Toast.makeText(QuizMainActivity.this,
							R.string.incorrect_toast, Toast.LENGTH_SHORT)
							.show();
				}
			}
		});

		mFalseButton = (Button) findViewById(R.id.false_button);
		// Setting listener
		mFalseButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (mQuestionBank[mCurrentIndex].isTrueQuestion() == false)
					Toast.makeText(QuizMainActivity.this,
							R.string.correct_toast, Toast.LENGTH_SHORT).show();
				else {
					Toast.makeText(QuizMainActivity.this,
							R.string.incorrect_toast, Toast.LENGTH_SHORT)
							.show();
				}
			}
		});

		mNextButton = (Button) findViewById(R.id.next_button);
		mNextButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
				updateQuestion();

			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.quiz_main, menu);
		return true;
	}
	@Override
	protected void onSaveInstanceState(Bundle outState){
		super.onSaveInstanceState(outState);
		outState.putInt(INDEX,mCurrentIndex);
		Log.d(TAG,"onSaveInstanceState() called");
	}
	
	@Override
	public void onStart(){
		super.onStart();
		Log.d(TAG,"onStart() called");
	}
	
	@Override
	public void onPause(){
		super.onPause();
		Log.d(TAG,"onPause() called");
	}
	
	@Override
	public void onResume(){
		super.onResume();
		Log.d(TAG,"onResume() called");
	}
	
	@Override
	public void onDestroy(){
		super.onDestroy();
		Log.d(TAG,"onDestroy() called");
	}
	
	@Override
	public void onStop(){
		super.onStop();
		Log.d(TAG,"onStop() called");
	}
}