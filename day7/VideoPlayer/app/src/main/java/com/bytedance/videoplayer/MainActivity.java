package com.bytedance.videoplayer;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView imageView = findViewById(R.id.imageView);
        String url = "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcTz-r9feZeUu-g-fsW1E5ssZ7vNG58n_gHxMA&usqp=CAU";
        RoundedCornersTransform transform = new RoundedCornersTransform(this, 100);
        transform.setNeedCorner(true, true, true, true);
        Glide.with(this).load(url).placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).transition(withCrossFade(1000)).fitCenter().transform(transform).into(imageView);

        Button btn = findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,VideoActivity.class);
                intent.putExtra("url","local");
                startActivity(intent);
            }
        });


        Intent intent = getIntent();
        String action = intent.getAction();
        Log.i("TAG","action:"+action);
        if(intent.ACTION_VIEW.equals(action))
        {

            Uri uri = intent.getData();
            //String str = Uri.decode(uri.getEncodedPath());
            //Log.v("TAG","str:"+str);
            Intent intentSend = new Intent(MainActivity.this,VideoActivity.class);
            intentSend.putExtra("url",uri.toString());
            startActivity(intentSend);
        }
    }
}
