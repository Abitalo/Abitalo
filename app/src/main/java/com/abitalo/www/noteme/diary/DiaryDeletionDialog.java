package com.abitalo.www.noteme.diary;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageButton;

import com.abitalo.www.noteme.Main;
import com.abitalo.www.noteme.R;

/**
 * Created by Lancelot on 2015/12/7.
 */
public class DiaryDeletionDialog extends DialogFragment {
    ImageButton submit_btn;
    ImageButton reject_btn;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        View view=inflater.inflate(R.layout.diary_delete_dialog, container);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        submit_btn = (ImageButton)getDialog().findViewById(R.id.diary_delete_submit_btn);
        reject_btn = (ImageButton)getDialog().findViewById(R.id.diary_delete_reject_btn);

        submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Main)getActivity()).deleteItem(getArguments().getInt("position"));
                dismiss();
            }
        });


        reject_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    public interface DiaryDeleteListener{
        void deleteItem(int position);
    }
}
