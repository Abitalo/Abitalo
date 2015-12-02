package com.abitalo.www.noteme.diary;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.abitalo.www.noteme.R;
import com.tekinarslan.material.FloatingActionButton;

/**
 * Created by Lancelot on 2015/9/27.
 */
public class DiaryFragment extends Fragment {
    private FloatingActionButton add_btn;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_diary, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        add_btn=(FloatingActionButton)getActivity().findViewById(R.id.diary_add_btn);
        add_btn.setDrawableIcon(getResources().getDrawable(R.drawable.plus));
        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//TODO function :create dialog to write diary
                Toast.makeText(getActivity(), "I'm pressed", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

