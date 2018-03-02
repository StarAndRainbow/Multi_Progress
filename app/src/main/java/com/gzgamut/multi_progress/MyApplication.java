package com.gzgamut.multi_progress;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.util.Log;

/**
 * 2018/3/1
 * guanbp@gzgamut.com
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        //打印当前进程名，每个进程跑起来的时候，都会执行一次Application.
        String processName = getCurProcessName();
        Log.e("TAG", "process name :" + processName);
    }

    /**
     * 获取当前进程名
     *
     * @return 进程名
     */
    private String getCurProcessName() {
        int pid = android.os.Process.myPid();
        ActivityManager mActivityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo appProcess : mActivityManager
                .getRunningAppProcesses()) {
            if (appProcess.pid == pid) {
                return appProcess.processName;
            }
        }
        return "获取失败";
    }

}
