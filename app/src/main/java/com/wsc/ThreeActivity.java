package com.wsc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

/**
 * @author shichao5
 * @date 2019/2/18
 * @describ
 */
public class ThreeActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView nameTv = findViewById(R.id.name);
        nameTv.setText("current task id:" + this.getTaskId() + "\t" + this);
        nameTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO
                startActivity(new Intent(ThreeActivity.this, MainActivity.class));
            }
        });
    }
}
