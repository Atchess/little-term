package com.bytedance.todolist.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.bytedance.todolist.R;
import com.bytedance.todolist.database.TodoListDao;
import com.bytedance.todolist.database.TodoListDatabase;
import com.bytedance.todolist.database.TodoListEntity;
import com.google.android.material.snackbar.Snackbar;

import java.util.Date;
import java.util.List;

public class NewItemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_item);

        final Button button = findViewById(R.id.newItem_btn);
        final EditText editText = findViewById(R.id.newItem_et);
        final Intent intent = new Intent(NewItemActivity.this, TodoListActivity.class);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread() {
                    @Override
                    public void run() {
                        TodoListDao dao = TodoListDatabase.inst(NewItemActivity.this).todoListDao();
                        dao.addTodo(new TodoListEntity(editText.getText().toString(), new Date(System.currentTimeMillis())));
                    }
                }.start();
                Snackbar.make(button, R.string.hint_insert_complete, Snackbar.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });
    }
}