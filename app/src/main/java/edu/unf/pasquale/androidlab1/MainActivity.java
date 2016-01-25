package edu.unf.pasquale.androidlab1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String KEY_INDEX = "index";
    private static final String KEY_ANSWER = "answer";

    private TextView mAnswerTV;
    private TextView mQuestionTV;

    private int mCurrentIndex = 0;
    private int[] mQuestionBank = new int[] {
            R.string.question_one,
            R.string.question_two,
            R.string.question_three
    };

    private String current_result;

    private boolean[] mAnswerBank = new boolean[] {
            true,
            false,
            true
    };

    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    private Button mHintButton;

    private boolean CheckAnswer(int question, boolean answerSelected) {
        if (mAnswerBank[question] == answerSelected) {
            return true;
        }

        else {
            return false;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mQuestionTV = (TextView)findViewById(R.id.question);

        if (savedInstanceState != null) {
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX);
            current_result = savedInstanceState.getString(KEY_ANSWER);
        }

        mQuestionTV.setText(mQuestionBank[mCurrentIndex]);
        mAnswerTV = (TextView)findViewById(R.id.answer);

        if (current_result != null) {
            mAnswerTV.setText(current_result);
        }

        mTrueButton = (Button)findViewById(R.id.true_button);
        mFalseButton = (Button)findViewById(R.id.false_button);
        mNextButton = (Button)findViewById(R.id.next_button);
        mHintButton = (Button)findViewById(R.id.hint_button);

        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (CheckAnswer(mCurrentIndex, true)) {
                    current_result = getString(R.string.correct_text);
                }

                else {
                    current_result = getString(R.string.incorrect_text);
                }

                mAnswerTV.setText(current_result);
            }
        });

        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (CheckAnswer(mCurrentIndex, false)) {
                    current_result = getString(R.string.correct_text);
                }

                else {
                    current_result = getString(R.string.incorrect_text);
                }

                mAnswerTV.setText(current_result);
            }
        });

        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                current_result = getString(R.string.answer_text);
                mAnswerTV.setText(current_result);
                mCurrentIndex = mCurrentIndex < 2 ? mCurrentIndex + 1 : 0;
                mQuestionTV.setText(mQuestionBank[mCurrentIndex]);
            }
        });

        mHintButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, HintActivity.class);
                boolean answerIsTrue = mAnswerBank[mCurrentIndex];
                i.putExtra(HintActivity.EXTRA_ANSWER, answerIsTrue);
                startActivity(i);
            }
        });

    }

    @Override
    public void onSaveInstanceState(Bundle savedState) {
        super.onSaveInstanceState(savedState);
        savedState.putInt(KEY_INDEX, mCurrentIndex);
        savedState.putString(KEY_ANSWER, current_result);
    }
}
