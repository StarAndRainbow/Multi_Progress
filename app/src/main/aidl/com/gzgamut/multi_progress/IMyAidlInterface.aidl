// IMyAidlInterface.aidl
package com.gzgamut.multi_progress;

// Declare any non-default types here with import statements

//首先在main目录下新建一个aidl目录，然后新建aidl文件，重新build一下项目，在build->generated->source->aidl->debug下面生成代码，getName方法是自己添加的
interface IMyAidlInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);

    String getName(String nickName);
}
