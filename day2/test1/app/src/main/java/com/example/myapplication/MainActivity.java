package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.myapplication.recycler.LinearItemDecoration;
import com.example.myapplication.recycler.MyAdapter;
import com.example.myapplication.recycler.TestData;
import com.example.myapplication.recycler.TestDataSet;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, MyAdapter.IOnItemClickListener {

    private static final String TAG = "TAG";

    private RecyclerView recyclerView;
    private MyAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private GridLayoutManager gridLayoutManager;
    private ViewNumCount count = new ViewNumCount();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        findViewById(R.id.btn_main_fans).setOnClickListener(this);
        findViewById(R.id.btn_main_comment).setOnClickListener(this);
        findViewById(R.id.btn_main_approve).setOnClickListener(this);
        findViewById(R.id.btn_main_atme).setOnClickListener(this);
        findViewById(R.id.recycler).setOnClickListener(this);
        initRecycleView();
        Log.i("TAG", "count: "+count.count(this.getWindow().getDecorView()));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_main_approve:
                Intent intent0 = new Intent(this, ApproveMeActivity.class);
                startActivity(intent0);
                break;
            case R.id.btn_main_atme:
                Intent intent1 = new Intent(this, AtMeActivity.class);
                startActivity(intent1);
                break;
            case R.id.btn_main_comment:
                Intent intent2 = new Intent(this, CommentMeActivity.class);
                startActivity(intent2);
                break;
            case R.id.btn_main_fans:
                Intent intent3 = new Intent(this, FansActivity.class);
                startActivity(intent3);
                break;
            /*case R.id.recycler:
                Intent intent4 = new Intent(this, TalkActivity.class);
                startActivity(intent4);
                break;*/
        }
    }

    private void initRecycleView() {
        recyclerView=findViewById(R.id.recycler);

        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new MyAdapter(TestDataSet.getData());
        mAdapter.setOnItemClickListener(this);
        recyclerView.setAdapter(mAdapter);
        LinearItemDecoration itemDecoration = new LinearItemDecoration(Color.BLUE);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

    }

    @Override
    public void onItemCLick(int position, TestData data) {
        Toast.makeText(MainActivity.this, "点击了第" + position + "条", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, TalkActivity.class);
        intent.putExtra("id",position);
        intent.putExtra("name",data.title);
        intent.putExtra("massage",data.massage);
        startActivity(intent);
    }

    @Override
    public void onItemLongCLick(int position, TestData data) {
        Toast.makeText(MainActivity.this, "长按了第" + position + "条", Toast.LENGTH_SHORT).show();
    }

}