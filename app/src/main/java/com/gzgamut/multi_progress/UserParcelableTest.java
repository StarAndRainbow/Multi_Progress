package com.gzgamut.multi_progress;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 2018/3/1
 * guanbp@gzgamut.com
 */
public class UserParcelableTest implements Parcelable{


    private String name ;
    private int age ;


    protected UserParcelableTest(Parcel in) {
        name = in.readString();
        age = in.readInt();
    }



    //序列化，由Parcel一系列的Write来完成
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(age);
    }


    //内容描述
    @Override
    public int describeContents() {
        return 0;
    }

    //反序列化
    public static final Creator<UserParcelableTest> CREATOR = new Creator<UserParcelableTest>() {
        @Override
        public UserParcelableTest createFromParcel(Parcel in) {
            return new UserParcelableTest(in);
        }

        @Override
        public UserParcelableTest[] newArray(int size) {
            return new UserParcelableTest[size];
        }
    };
}
