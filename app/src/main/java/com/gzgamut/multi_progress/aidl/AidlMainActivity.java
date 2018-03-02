package com.gzgamut.multi_progress.aidl;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.gzgamut.multi_progress.IMyAidlInterface;
import com.gzgamut.multi_progress.R;

/**
 * 2018/2/26
 * guanbp@gzgamut.com
 */

//序列化插件Parcelable code generate    在实体类中alt + insert
public class AidlMainActivity extends AppCompatActivity {

    private Button mBtnAidl;

    //持有有AidI接口
    private IMyAidlInterface mIMyAidlInterface;

    ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            //实例对象
            mIMyAidlInterface = IMyAidlInterface.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBtnAidl = (Button) findViewById(R.id.bt_hello);

        bindService(new Intent(AidlMainActivity.this, AidlService.class), mServiceConnection, BIND_AUTO_CREATE);

        mBtnAidl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mIMyAidlInterface != null){
                    try {
                        //调用方法
                        String name = mIMyAidlInterface.getName("test");
                        Toast.makeText(AidlMainActivity.this, "name = " + name, Toast.LENGTH_SHORT).show();
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(mServiceConnection);
    }


}

