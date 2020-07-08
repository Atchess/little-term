package com.example.test0;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView tv1 = findViewById(R.id.text_title);
        Button button1 = findViewById(R.id.button_test);
        Switch sw1 = findViewById(R.id.switch1);
        final ConstraintLayout myLayout = findViewById(R.id.page1);
        myLayout.setBackgroundColor(Color.WHITE);
        final EditText email = findViewById(R.id.editTextTextEmailAddress);
        final EditText password = findViewById(R.id.editTextTextPassword);

        button1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Editable address = email.getText();
                Editable passwd = password.getText();
                if ( (address.toString().equals("example@example.com")) && passwd.toString().equals("password") ){
                    Log.i("login", "onCreate: login success");
                    tv1.setText("账号正确");
                    Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                    startActivity(intent);
                }
                else
                    tv1.setText("密码或账号错误");
                Log.i("address", "address: " + address.toString());
                Log.i("password", "password: " + passwd.toString());

            }
        });

        sw1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){

            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    myLayout.setBackgroundColor(Color.BLUE);
                    Log.i("theme", "color: blue");
                }
                else {
                    myLayout.setBackgroundColor(Color.WHITE);
                    Log.i("theme", "color: white");
                }
            }
        });

    }
}