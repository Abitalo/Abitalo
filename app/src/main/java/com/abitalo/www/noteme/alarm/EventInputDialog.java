package com.abitalo.www.noteme.alarm;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.abitalo.www.noteme.Main;
import com.abitalo.www.noteme.R;


/**
 * Created by Lancelot on 2015/11/29.
 */
public class EventInputDialog extends DialogFragment{
    Button submit_btn;
    EditText event_add_text;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        View view=inflater.inflate(R.layout.alarm_add_content,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        submit_btn=(Button)getDialog().findViewById(R.id.sure_button);
        event_add_text=(EditText)getDialog().findViewById(R.id.alarm_content);
        submit_btn.setOnClickListener(new submitOnclickListener());
    }

    private class submitOnclickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            String time = getArguments().getInt("hour") + ":" + getArguments().getInt("minute");
            String text = event_add_text.getText().toString();
            if(text.replaceAll(" ","").equals("")){
                Toast.makeText(getActivity(), "所以你究竟要做什么呢？(⊙v⊙)。", Toast.LENGTH_SHORT).show();
            }
            else {
                ((Main) getActivity()).EventInputComplete(new Item_Alarm(time, text));
            }
            dismiss();
        }
    }

    public interface AlarmEventInputListener {
        void EventInputComplete(Item_Alarm newItem);
    }


}