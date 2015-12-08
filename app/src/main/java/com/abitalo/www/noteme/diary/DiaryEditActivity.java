package com.abitalo.www.noteme.diary;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.abitalo.www.noteme.R;

/**
 * Created by Lancelot on 2015/12/7.
 */
public class DiaryEditActivity extends Activity {
    private EditText title;
    private EditText content;
    private TextView timeText;
    private ImageButton submit_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diary_edit);

        init();
    }

    private void init(){
        title=(EditText)findViewById(R.id.diary_edit_title);
        content=(EditText)findViewById(R.id.diary_edit_content);
        timeText=(TextView)findViewById(R.id.opr_time_line_text);
        submit_btn=(ImageButton)findViewById(R.id.diary_edit_submit_btn);

        Intent intent=getIntent();
        boolean isEditing=intent.getBooleanExtra("isEditing",false);
        if(true == isEditing){// a activity for CREATE a diary

        }
        else// a activity for EDIT a diary
            return ;
    }
}
