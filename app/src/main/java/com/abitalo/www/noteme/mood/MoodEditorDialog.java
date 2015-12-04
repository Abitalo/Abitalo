package com.abitalo.www.noteme.mood;

import android.support.v4.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.abitalo.www.noteme.Main;
import com.abitalo.www.noteme.R;

import java.util.Calendar;


/**
 * Created by Lancelot on 2015/11/29.
 */
public class MoodEditorDialog extends DialogFragment{
    ImageButton submit_btn;
    EditText mood_edit_text;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        View view=inflater.inflate(R.layout.mood_editor,container);

        return view;
    }
    private class submitOnclickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Calendar now=Calendar.getInstance();
            String text=mood_edit_text.getText().toString();
            if(text.replaceAll(" ","").equals("")){
                Toast.makeText(getActivity(),"心情不能为空的说=。=||",Toast.LENGTH_SHORT).show();
            }
            else{
                ((Main)getActivity()).moodEditComplete(new Item_Mood(now,text));
            }
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

        submit_btn=(ImageButton)getDialog().findViewById(R.id.mood_submit_btn);
        mood_edit_text=(EditText)getDialog().findViewById(R.id.mood_edit_text);
        submit_btn.setOnClickListener(new submitOnclickListener());
    }
}
