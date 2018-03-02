package com.gzgamut.multi_progress.aidl;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import com.gzgamut.multi_progress.IMyAidlInterface;


/**
 * 2018/2/26
 * guanbp@gzgamut.com
 */


public class AidlService extends Service {
    //TODO  1生成一个IMyAidIInterface.stub

    IMyAidlInterface.Stub mStub = new IMyAidlInterface.Stub(){
        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }

        //TODO 2，重写方法
        @Override
        public String getName(String nickName) throws RemoteException {
            return "aidl " + nickName;
        }
    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mStub;
    }

}

