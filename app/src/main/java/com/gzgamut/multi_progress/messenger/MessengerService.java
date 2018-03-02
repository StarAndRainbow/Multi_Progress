package com.gzgamut.multi_progress.messenger;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.support.annotation.Nullable;
import android.widget.Toast;

/**
 * 2018/2/26
 * guanbp@gzgamut.com
 */

//使用Messenger进行多线程通信，服务和activity运行在不同的线程

public class MessengerService  extends Service{

    //TODO 1.
    private Handler messengerHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Toast.makeText(getApplicationContext(),"接收到了信息",Toast.LENGTH_SHORT).show();
        }
    };

    //TODO 2.使用Messenger进行多线程的通信,需要一个Handller,在onBind中返回messenger.getBinding()即可;
    private Messenger messenger  = new Messenger(messengerHandler);

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        //TODO 3.
        return messenger.getBinder();
    }
}
