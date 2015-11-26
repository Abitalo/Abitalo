package com.abitalo.www.noteme;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;




/**
 * Created by Lancelot on 2015/9/27.
 */
public class AlarmFragment extends Fragment {
    private ImageButton list;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_alarm,container,false);
    }
}
