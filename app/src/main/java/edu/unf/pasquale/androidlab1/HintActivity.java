package edu.unf.pasquale.androidlab1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HintActivity extends AppCompatActivity {

    private static final String KEY_HINT = "hint";

    private String current_hint;

    private Button mShowAnswerButton;
    private TextView mHintTextView;

    public static final String EXTRA_ANSWER = "extra_answer";
    public static final String EXTRA_ANSWER_SHOW = "extra_answer_show";
    private boolean mAnswerIsTrue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hint);

        mHintTextView = (TextView)findViewById(R.id.confirm_text);

        if (savedInstanceState != null) {
            current_hint = savedInstanceState.getString(KEY_HINT);
            if (current_hint != null) {
                mHintTextView.setText(current_hint);
            }

        }

        mAnswerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER, false);
        mShowAnswerButton = (Button)findViewById(R.id.show_answer_btn);

        mShowAnswerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                current_hint = mAnswerIsTrue ? getString(R.string.hint_true) : getString(R.string.hint_false);
                mHintTextView.setText(current_hint);
            }
        });
    }

    private void setAnswerShownResult(boolean isAnswerShown) {
        Intent data = new Intent();
        //To be continued
    }

    @Override
    public void onSaveInstanceState(Bundle savedState) {
        super.onSaveInstanceState(savedState);
        savedState.putString(KEY_HINT, current_hint);
    }
}
