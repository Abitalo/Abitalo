package com.abitalo.www.noteme.mood;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import com.abitalo.www.noteme.R;

/**
 * Created by Lancelot on 2015/11/29.
 */
public class MoodEditorDialog extends Dialog {
    public MoodEditorDialog(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mood_editor);
    }
}
