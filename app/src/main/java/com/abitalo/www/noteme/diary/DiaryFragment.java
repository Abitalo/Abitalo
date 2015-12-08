package com.abitalo.www.noteme.diary;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.abitalo.www.noteme.R;
import com.abitalo.www.noteme.Varible;
import com.tekinarslan.material.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lancelot on 2015/9/27.
 * Container for diary function
 */
public class DiaryFragment extends Fragment implements MasonryView.OnItemClickListener,MasonryView.OnItemLongClickListener{
    private View view;
    //RecyclerView for implements Masonry Style of diaries.
    private RecyclerView recyclerView;
    //A list containing the data of each diary
    private List<Item_Diary> data;
    //Adapter to fill the RecyclerView
    private MasonryAdapter adapter;
    //Button to write a new diary
    private FloatingActionButton add_btn;
    private AlertDialog myDialog;
    private int pos;

    //Request code for start a edit activity
    //carry the message of item's position ,so i made it no-final
    private static int DIARY_EDIT_REQUEST;

    //Request code for start a create activity
    //choose this value to avoid conflict with item's position message ,make it final because it carries no information
    //!! request code must be positive ,or it can't be receive by onActivityResulted() method
    private static final int DIARY_CREATE_REQUEST=0xffff;

    //Result code for pressed submit button in @com.abitalo.www.noteme.diary.DiaryEditActivity
    private static final int RESULT_OK =0;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_diary, container, false);

        init();
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        add_btn=(FloatingActionButton)getActivity().findViewById(R.id.diary_add_btn);
        add_btn.setDrawableIcon(getResources().getDrawable(R.drawable.plus));
        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), DiaryEditActivity.class);
                startActivityForResult(intent, DIARY_CREATE_REQUEST);
            }
        });
    }

    private void init(){
        data=new ArrayList<Item_Diary>();
        initDatabase();
        initRecyclerView();
    }

    public void initDatabase(){
        Cursor cursor= Varible.db.rawQuery("select TITLE,CONTENT,DATE from TDIARY where USERNAME='abitalo'",null);
        if(null != cursor){
            while(cursor.moveToNext()){
                data.add(new Item_Diary(cursor.getString(0),cursor.getString(1),cursor.getLong(2)));
            }
            cursor.close();
        }
    }

    private void initRecyclerView(){
        recyclerView=(RecyclerView)view.findViewById(R.id.diary_recycler);
        adapter=new MasonryAdapter(data);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter.setOnItemClickListener(this);
        adapter.setOnItemLongClickListener(this);
        //TODO setOnItemClickListener

    }

    @Override
    public void onClick(View v, int position) {
        DIARY_EDIT_REQUEST=position;
        Intent intent = new Intent();
        intent.setClass(getActivity(), DiaryEditActivity.class);
        intent.putExtra("timezone",data.get(position).getDate().getTimeInMillis());
        startActivityForResult(intent, DIARY_EDIT_REQUEST);
    }

    @Override
    public void onLongClick(View v, int position) {
        pos=position;
        myDialog = new AlertDialog.Builder(getActivity()).create();
        myDialog.show();
        myDialog.getWindow().setContentView(R.layout.diary_delete_dialog);
        myDialog.getWindow()
                .findViewById(R.id.diary_delete_submit_btn)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        deleteItem(pos);
                        myDialog.dismiss();
                    }
                });
        myDialog.getWindow()
                .findViewById(R.id.diary_delete_reject_btn)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        myDialog.dismiss();
                    }
                });
    }

    public void deleteItem(int position){
        data.remove(position);
        adapter.notifyItemRemoved(position);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
        if (RESULT_OK == resultCode){                       //edit activity finished
            //fetch the args
            Bundle args = data.getBundleExtra("args");
            String title=args.getString("title");
            String content=args.getString("content");
            Long date=args.getLong("date");
                if(DIARY_EDIT_REQUEST == requestCode){      //after edit action
                    //update the view
                    this.data.get(DIARY_EDIT_REQUEST).setTitle(title);
                    this.data.get(DIARY_EDIT_REQUEST).setText(content);
                    adapter.notifyDataSetChanged();

                    //update the database
                    ContentValues values=new ContentValues();
                    values.put("TITLE",title);
                    values.put("CONTENT",content);
                    Varible.db.update("TDIARY",values,"DATE=?",new String[]{String.valueOf(date)});
                }
                else if(DIARY_CREATE_REQUEST == requestCode){
                    //update the view
                    this.data.add(new Item_Diary(title, content, date));
                    adapter.notifyDataSetChanged();

                    //update the database
                    ContentValues values=new ContentValues();
                    values.put("TITLE",title);
                    values.put("CONTENT",content);
                    values.put("DATE",date);
                    values.put("USERNAME","abitalo");
                    Varible.db.insert("TDIARY", null, values);
                }
        }
    }
}

