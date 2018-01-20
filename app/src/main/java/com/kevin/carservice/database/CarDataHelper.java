package com.kevin.carservice.database;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.kevin.carservice.base.BaseApplication;

/**
 * Created by <a href="http://blog.csdn.net/student9128">Kevin</a> on 2018/1/20.
 * <h3>Description:</h3>
 * <div>
 * </div>
 */


public class CarDataHelper extends SQLiteOpenHelper {

    private static CarDataHelper INSTANCE;

    public static CarDataHelper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new CarDataHelper(BaseApplication.getContext());
        }
        return INSTANCE;
    }

    public CarDataHelper(Context context) {
        super(context, CarTable.DB_NAME, null, CarTable.DB_VERSION);
    }

    public CarDataHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public CarDataHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //create table if not exists carTable(_id integer primary key, carNumber text)
//        String sql = "CREATE TABLE IF NOT EXISTS " + CarTable.TABLE_NAME + "(" + "_id"
//                + " INTEGER PRIMARY KEY," + CarTable.COLUMN_CAR_NUMBER + CarTable.DATA_TYPE_TEXT + ")";
        String sql = "create table if not exists carTable1(_id integer primary key, carNumber varchar,status varchar,time varchar);";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String sql = "DROP TABLE IF EXISTS " + CarTable.TABLE_NAME;
        sqLiteDatabase.execSQL(sql);
        onCreate(sqLiteDatabase);
    }
}
