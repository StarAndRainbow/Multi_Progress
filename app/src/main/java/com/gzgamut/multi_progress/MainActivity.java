package com.gzgamut.multi_progress;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * 2018/3/1
 * guanbp@gzgamut.com
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //通过不同进程打印userID静态变量，不同进程更改变量无效（MainActivity,SecondActivity）
        UserManger.userId = 2;
        Log.e("userId","MainActivity:"+UserManger.userId);
       findViewById(R.id.bt_Serializable).setOnClickListener(this);
    }



    //启动第二个Activity和启动第三个Activity
    public void startUpSecondActivity(View view){
        startActivity(new Intent(this,SecondActivity.class));
    }


    public void startUpThreeActivity(View view ){
        startActivity(new Intent(this,ThreeActivity.class));
    }


    private int REQUEST_CODE = 0x123;
    private boolean havePermission(){
        if(PackageManager.PERMISSION_GRANTED !=(ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE))){
            return false;
        }else {
            return true;
        }
    }


    //判断是否有权限
    private void getRunPermission(){
        if(PackageManager.PERMISSION_GRANTED !=(ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE))){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE);
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode ==REQUEST_CODE && grantResults[0] == PackageManager.PERMISSION_GRANTED ){
            testSerizable();
        }else{
            Toast.makeText(this,"写入内存卡权限被拒绝",Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 试着写入一个对象
     */
    private void testSerizable(){
       UserSerializableTest user = new UserSerializableTest("linxin",28);
        //写入一个序列化对象
        try {
            ObjectOutputStream  outputStream = new ObjectOutputStream(new FileOutputStream( Environment.getExternalStorageDirectory().toString()+ File.separator + "cache.txt"));
            outputStream.writeObject(user);
            outputStream.getClass();
        } catch (IOException e) {
            e.printStackTrace();
        }


        //读出一个序列化对象
        try {
            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream( Environment.getExternalStorageDirectory().toString()+ File.separator + "cache.txt"));
            try {
                UserSerializableTest user1 = (UserSerializableTest)inputStream.readObject();
                Log.e("user1", "testSerizable: "+user1.getName() );
                Log.e("user1", "testSerizable: "+user1.getAge() );
            } catch (ClassNotFoundException e){
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onClick(View v) {
        if(!havePermission()){
            getRunPermission();
        }else{
            testSerizable();
        }
    }
}
