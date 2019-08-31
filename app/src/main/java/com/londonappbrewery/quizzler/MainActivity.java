package com.londonappbrewery.quizzler;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    // TODO: Declare constants here


    // TODO: Declare member variables here:
    Button mTrueButton,mFalseButton;
    TextView mQuestionTextView,mScoreTextView;
    ProgressBar mProgressBar;
    int mIndex = 0;
    int score = 0;
    // TODO: Uncomment to create question bank
    private TrueFalse[] mQuestionBank = new TrueFalse[] {
            new TrueFalse(R.string.question_1, true),
            new TrueFalse(R.string.question_2, true),
            new TrueFalse(R.string.question_3, true),
            new TrueFalse(R.string.question_4, true),
            new TrueFalse(R.string.question_5, true),
            new TrueFalse(R.string.question_6, false),
            new TrueFalse(R.string.question_7, true),
            new TrueFalse(R.string.question_8, false),
            new TrueFalse(R.string.question_9, true),
            new TrueFalse(R.string.question_10, true),
            new TrueFalse(R.string.question_11, false),
            new TrueFalse(R.string.question_12, false),
            new TrueFalse(R.string.question_13,true)
    };
    final int MPROGRESSINCREMENT =(int) Math.ceil(100.0/mQuestionBank.length);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //saveInstanceState is null at the starting of the app but it will not be null at the time of screen rotation
        if(savedInstanceState!=null)
        {
            score = savedInstanceState.getInt("Score");
            mIndex = savedInstanceState.getInt("IndexKey");
        }
        else
        {
            score = 0;
            mIndex = 0;
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTrueButton = (Button) findViewById(R.id.true_button);
        mFalseButton = (Button) findViewById((R.id.false_button));
        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);
        mScoreTextView = (TextView) findViewById(R.id.score);
        mProgressBar = (ProgressBar) findViewById(R.id.progress_bar);
        mIndex = 0;
        mScoreTextView.setText("Score "+score+"/"+mQuestionBank.length);
        mQuestionTextView.setText(mQuestionBank[mIndex].getQuestionID());
        //another way of adding listener to a view...not a anonymous listener....can be used with different views
//        View.OnClickListener myListener = new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.d("Quizzler","Button Pressed");
//                //anonymous toast message
//                Toast.makeText(getApplicationContext(),"True Pressed",Toast.LENGTH_SHORT).show();
//            }
//        };
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Log.d("Quizzler","Button Pressed");
//                //anonymous toast message
//                Toast.makeText(getApplicationContext(),"True Pressed",Toast.LENGTH_SHORT).show();
                if(mQuestionBank[mIndex].isAnswer() == true) {
                    Toast.makeText(getApplicationContext(), "Correct", Toast.LENGTH_SHORT).show();
                    score++;
                    mScoreTextView.setText("Score "+score+"/"+mQuestionBank.length);
                }
                else
                    Toast.makeText(getApplicationContext(),"Wrong",Toast.LENGTH_SHORT).show();
                mProgressBar.incrementProgressBy(MPROGRESSINCREMENT);
                updateQuestion();
            }
        });
        //anonymous Listener....conventional way
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //toast is a message which pops on the bottom of a screen for few seconds ....such as "message saved as draft in gmail app.
//                Toast myToast = Toast.makeText(getApplicationContext(),"False Pressed",Toast.LENGTH_SHORT);
//                myToast.show();
                if(mQuestionBank[mIndex].isAnswer() == false) {
                    Toast.makeText(getApplicationContext(), "Correct", Toast.LENGTH_SHORT).show();
                    score++;
                    mScoreTextView.setText("Score "+score+"/"+mQuestionBank.length);
                }
                else
                    Toast.makeText(getApplicationContext(),"Wrong",Toast.LENGTH_SHORT).show();
                mProgressBar.incrementProgressBy(MPROGRESSINCREMENT);
                updateQuestion();
            }
        });
        //an example to use our TrueFalse class
//        TrueFalse exampleQuestion = new TrueFalse(R.string.question_1,true);
    }
    private void updateQuestion()
    {
        mIndex = (mIndex+1)%mQuestionBank.length;
        if(mIndex==0)
        {
            //AlertBuilder is a popup tab which appears in the middle of the screen
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("Game Over");
            alert.setCancelable(false);
            alert.setMessage("You Scored "+score+" points");
            alert.setPositiveButton("Close Application", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            alert.show();
        }
        mQuestionTextView.setText(mQuestionBank[mIndex].getQuestionID());
    }
    //this can be used in place of getApplicationContext()
    //below method is used at the time of screen rotation to save the instance of our app so that everything does not start from the beginning

    @Override
    public void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
        outState.putInt("Score",score);
        outState.putInt("IndexKey",mIndex);
    }
}
