package com.gzgamut.multi_progress;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

/**
 * 2018/3/1
 * guanbp@gzgamut.com
 */
public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e("userId","SecondActivity:"+UserManger.userId);
    }

    public void startUpSecondActivity(View view){
        startActivity(new Intent(this,SecondActivity.class));
    }

    public void startUpThreeActivity(View view ){
        startActivity(new Intent(SecondActivity.this,ThreeActivity.class));
    }

}
