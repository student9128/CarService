package com.kevin.carservice.base;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.kevin.carservice.cardatadao.DaoMaster;
import com.kevin.carservice.cardatadao.DaoSession;

/**
 * Created by <a href="http://blog.csdn.net/student9128">Kevin</a> on 2017/12/21.
 * <h3>Description:</h3>
 * <div>
 * </div>
 */


public class BaseApplication extends Application {
    private static Context mContext;
    private DaoMaster.DevOpenHelper mHelper;
    private SQLiteDatabase sqLiteDatabase;
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;
    private static BaseApplication INSTANCE;
    public static Context getContext() {
        return mContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        INSTANCE = this;

    }
}
