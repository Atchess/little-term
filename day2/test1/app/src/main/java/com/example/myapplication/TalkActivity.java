package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.recycler.ChatAdapter;
import com.example.myapplication.recycler.ChatData;
import com.example.myapplication.recycler.ChatDataSet;
import com.example.myapplication.recycler.LinearItemDecoration;
import com.example.myapplication.recycler.MyAdapter;
import com.example.myapplication.recycler.TestData;
import com.example.myapplication.recycler.TestDataSet;

import java.util.ArrayList;
import java.util.List;

public class TalkActivity extends Activity implements View.OnClickListener, ChatAdapter.IOnItemClickListener {

    private static final String TAG = "TAG";
    private ChatAdapter chat_adapter;
    private List<ChatData> chat_data_set = new ArrayList();
    private EditText edittext;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private GridLayoutManager gridLayoutManager;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_talk);
        TextView tv0 = findViewById(R.id.person_id);
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        Log.i(TAG, "name: " + name);
        tv0.setText(name);
        findViewById(R.id.btn_send).setOnClickListener(this);
        String massage = intent.getStringExtra("massage");
        Log.i(TAG,massage);
        ChatData data = new ChatData("dsa",massage,0);
        chat_data_set.add(data);
        chat_adapter = new ChatAdapter(chat_data_set);
        chat_adapter.setOnItemClickListener(this);
        edittext = findViewById(R.id.chat_edittext);
        initRecycleView();
    }

    private void initRecycleView() {
        recyclerView=findViewById(R.id.talk_recycler);

        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(chat_adapter);
        LinearItemDecoration itemDecoration = new LinearItemDecoration(Color.BLUE);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

    }

    @Override
    public void onClick(View view) {
        Log.i("TAG",edittext.getText().toString());
        chat_adapter.addData(chat_adapter.getItemCount(),new ChatData("sdf",edittext.getText().toString(),1));
        edittext.setText("");
    }

    @Override
    public void onItemCLick(int position, ChatData data) {

    }

    @Override
    public void onItemLongCLick(int position, ChatData data) {

    }
}
