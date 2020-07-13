package com.bytedance.todolist.activity;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bytedance.todolist.R;
import com.bytedance.todolist.database.TodoListDao;
import com.bytedance.todolist.database.TodoListDatabase;
import com.bytedance.todolist.database.TodoListEntity;
import com.google.android.material.snackbar.Snackbar;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author wangrui.sh
 * @since Jul 11, 2020
 */
public class TodoListItemHolder extends RecyclerView.ViewHolder {
    private TextView mContent;
    private TextView mTimestamp;
    private final CheckBox checkBox = itemView.findViewById(R.id.item_checkBox);
    Long id;
    String content;
    Date time;

    public TodoListItemHolder(@NonNull final View itemView, final TodoListActivity mActivity) {
        super(itemView);
        mContent = itemView.findViewById(R.id.tv_content);
        mTimestamp = itemView.findViewById(R.id.tv_timestamp);
        final TextView mContent = itemView.findViewById(R.id.tv_content);
        final TextView mTimestamp = itemView.findViewById(R.id.tv_timestamp);
        checkBox.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener(){

            @Override
            public void onCheckedChanged(CompoundButton compoundButton, final boolean b) {
                final TodoListEntity entity = new TodoListEntity(content,time);
                if (b) {
                    entity.setId(id);
                    entity.setIsFinish(true);
                    mContent.setTextColor(Color.GRAY);
                    mContent.setPaintFlags(mContent.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                }
                else {
                    entity.setId(id);
                    mContent.setTextColor(Color.BLACK);
                    mContent.setPaintFlags(mContent.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
                }
                 new Thread() {
                    @Override
                    public void run() {
                        TodoListDao dao = TodoListDatabase.inst(mActivity).todoListDao();
                        dao.deleteTodo(id);
                        dao.addTodo(entity);
                    }
                }.start();
                //mActivity.loadFromDatabase();
            }
        });

        final ImageButton button = itemView.findViewById(R.id.item_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread() {
                    @Override
                    public void run() {
                        TodoListDao dao = TodoListDatabase.inst(mActivity).todoListDao();
                        dao.deleteTodo(id);
                    }
                }.start();
                Snackbar.make(button, R.string.hint_delete_complete, Snackbar.LENGTH_SHORT).show();
                mActivity.loadFromDatabase();
            }
        });
    }


    public void bind(TodoListEntity entity) {
        boolean isFinish;
        id = entity.getId();
        isFinish = entity.getIsFinish();
        content = entity.getContent();
        time = entity.getTime();

        if (isFinish==true) {
            mContent.setTextColor(Color.GRAY);
            mContent.setPaintFlags(mContent.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            mContent.setText(entity.getContent());
            mTimestamp.setText(formatDate(entity.getTime()));
            checkBox.setChecked(true);
        }
        else {
            mContent.setText(entity.getContent());
            mTimestamp.setText(formatDate(entity.getTime()));
        }

    }

    private String formatDate(Date date) {
        DateFormat format = SimpleDateFormat.getDateInstance();
        return format.format(date);
    }
}
