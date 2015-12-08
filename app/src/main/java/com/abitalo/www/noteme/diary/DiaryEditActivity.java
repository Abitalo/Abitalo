package com.abitalo.www.noteme.diary;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.abitalo.www.noteme.R;
import com.abitalo.www.noteme.Varible;

import java.util.Calendar;

/**
 * Created by Lancelot on 2015/12/7.
 */
public class DiaryEditActivity extends Activity {
    private static final int RESULT_SUBMIT =0;
    private static final int RESULT_BACK =1;
    private EditText title;
    private EditText content;
    private TextView timeText;
    private ImageButton submit_btn;
    boolean IN_EDIT_MODE;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diary_edit);
        init();
        submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                Bundle args=new Bundle();
                args.putString("title", title.getText().toString());
                args.putString("content", content.getText().toString());
                if(IN_EDIT_MODE)   // if in edit mode ,keep the original timezone
                    args.putLong("date",getIntent().getLongExtra("timezone",-1));
                else{                    // if in creating mode ,set the current timezone
                    args.putLong("date", Calendar.getInstance().getTimeInMillis());
                    Log.i("Info", "returning");
                }

                i.putExtra("args", args);
                setResult(RESULT_SUBMIT, i);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        setResult(RESULT_BACK);
        super.onBackPressed();
    }

    private void init(){
        long timezone=getIntent().getLongExtra("timezone",-1);
        IN_EDIT_MODE = (timezone != -1);//see if in edit mode

        title=(EditText)findViewById(R.id.diary_edit_title);
        content=(EditText)findViewById(R.id.diary_edit_content);
        timeText=(TextView)findViewById(R.id.opr_time_line_text);
        submit_btn=(ImageButton)findViewById(R.id.diary_edit_submit_btn);

            Cursor cursor= Varible.db.rawQuery
                    ("select TITLE,CONTENT from TDIARY where USERNAME='abitalo' and DATE="+String.valueOf(timezone),null);
            if(null != cursor){
                while(cursor.moveToNext()){
                    title.setText(cursor.getString(0));
                    content.setText(cursor.getString(1));
                    Log.i("Info",String.valueOf(timezone));
                    timeText.setText(Item_Diary.getDateString(timezone));
                }
                cursor.close();
            }
    }
}
