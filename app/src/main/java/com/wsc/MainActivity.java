package com.wsc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.wsc.weibo.TranslateCustomPathView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.view_chatlistview);
//        setContentView(new TranslateCustomPathView(this));
        setContentView(R.layout.activity_main);
        TextView nameTv = findViewById(R.id.name);
        nameTv.setText("current task id:" + this.getTaskId() + "\t" + this);
        nameTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO
                startActivity(new Intent(MainActivity.this, SecondActivity.class));
            }
        });
    }
}

