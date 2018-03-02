package com.gzgamut.multi_progress.messenger;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.gzgamut.multi_progress.R;

public class MessengerMainActivity extends AppCompatActivity implements View.OnClickListener {



    private Messenger mMessenger;
    private boolean mBound;


    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            //Todo 2,使用IBinder将Messenger（引用服务的Handler）实例化，然后使用后者将Message对象发送给服务。
            mMessenger = new Messenger(service);
            mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mMessenger = null;
            mBound = false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.bt_sayHi).setOnClickListener(this);
    }



    @Override
    protected void onResume() {
        super.onResume();
    }


    @Override
    protected void onStart() {
        super.onStart();
        //TODO  1,启动服务
        Intent intent = new Intent(this,MessengerService.class);
        bindService(intent,serviceConnection,BIND_AUTO_CREATE);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mBound){
            mMessenger = null;
            unbindService(serviceConnection);
        }
    }

    @Override
    public void onClick(View v) {
        //Todo 3,
        if(!mBound){
            Toast.makeText(this,"mBound为null",Toast.LENGTH_SHORT).show();
            return;
        }
        Message message = Message.obtain();
        message.what = 1;
        try {
            mMessenger.send(message);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
