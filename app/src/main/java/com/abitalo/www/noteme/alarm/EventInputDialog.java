package com.abitalo.www.noteme.alarm;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.abitalo.www.noteme.Main;
import com.abitalo.www.noteme.R;
import com.abitalo.www.noteme.mood.Item_Mood;

import java.util.Calendar;


/**
 * Created by Lancelot on 2015/11/29.
 */
public class EventInputDialog extends DialogFragment{
    Button submit_btn;
    EditText event_add_text;
    String content;
    boolean isClicked;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        View view=inflater.inflate(R.layout.alarm_add_content,container,false);
        isClicked = false;
        return view;
    }
    private class submitOnclickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
//            Calendar now=Calendar.getInstance();
            content=event_add_text.getText().toString();
            isClicked=true;
//            ((Main)getActivity()).moodEditComplete(new Item_Mood(now,text));
            dismiss();
        }
    }

    public interface MoodEditListener
    {
        void moodEditComplete(Item_Mood newItem);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        submit_btn=(Button)getDialog().findViewById(R.id.sure_button);
        event_add_text=(EditText)getDialog().findViewById(R.id.alarm_content);
        submit_btn.setOnClickListener(new submitOnclickListener());
    }
}
