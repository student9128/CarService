package com.kevin.carservice.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.kevin.carservice.base.BaseApplication;
import com.kevin.carservice.utils.LogK;

/**
 * Created by <a href="http://blog.csdn.net/student9128">Kevin</a> on 2018/1/20.
 * <h3>Description:</h3>
 * <div>
 * </div>
 */


public class CarDataHelper extends SQLiteOpenHelper {

    private static CarDataHelper INSTANCE;
    private static final String TAG = "CarDataHelper.class";

    public static CarDataHelper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new CarDataHelper(BaseApplication.getContext());
        }
        return INSTANCE;
    }

    public CarDataHelper(Context context) {
        super(context, CarTable.DB_NAME, null, CarTable.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "create table if not exists " + CarTable.TABLE_NAME
                + "(_id integer primary key autoincrement, " + CarTable.COLUMN_CAR_NUMBER + " text,"
                + CarTable.COLUMN_CAR_STATUS + " text," + CarTable.COLUMN_CAR_TIME + " text,"
                + CarTable.COLUMN_STATUS_COLOR + " text);";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String sql = "DROP TABLE IF EXISTS " + CarTable.TABLE_NAME;
        sqLiteDatabase.execSQL(sql);
        onCreate(sqLiteDatabase);
        LogK.d(TAG, "onUpgrade: upgrade database");
    }
}
