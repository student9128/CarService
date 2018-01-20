package com.kevin.carservice.database;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.kevin.carservice.bean.CarMaintainBean;

import java.util.List;

/**
 * Created by <a href="http://blog.csdn.net/student9128">Kevin</a> on 2018/1/20.
 * <h3>Description:</h3>
 * <div>
 * </div>
 */


public class CarDao {
    private CarDataHelper carDataHelper;
    private SQLiteDatabase sqLiteDatabase;

    public CarDao() {
        carDataHelper = CarDataHelper.getInstance();
    }

    public void insert(String carNumber, String status, String time) {
        sqLiteDatabase = carDataHelper.getWritableDatabase();
//        "insert into carTable(carNumber) values(?)";
//        String sql = "INSERT INTO " + CarTable.TABLE_NAME + "(" + CarTable.COLUMN_CAR_NUMBER + ") VALUES (苏)";
//        String sql = "INSERT INTO " + CarTable.TABLE_NAME
//                + "(\"" + CarTable.COLUMN_CAR_NUMBER + "\") VALUES (\"" + carNumber + " \");";
        String sql = "insert into carTable1(\"carNumber\",\"status\",\"time\") values(\""
                + carNumber + "\",\"" + status + "\",\"" + time + "\")";
        sqLiteDatabase.execSQL(sql);
    }

    public void queryByKey(String key) {
        sqLiteDatabase = carDataHelper.getWritableDatabase();
//        "select * from carTable where carNumber like '%?%'";
        String sql = "SELECT * FROM " + CarTable.TABLE_NAME + " WHERE " + CarTable.COLUMN_CAR_NUMBER + " LIKE %" + key + "%";
        sqLiteDatabase.execSQL(sql);
    }

    public List<CarMaintainBean.CTNTBean> queryByKeyLetter(List<CarMaintainBean.CTNTBean> strings, String key) {
        sqLiteDatabase = carDataHelper.getWritableDatabase();
//        String s = "select * from carTable where \"carNumber\" like '%?%'";
//        sqLiteDatabase.query("carTable","carNumber",)
        Cursor cursor = sqLiteDatabase.query(CarTable.TABLE_NAME, new String[]{"carNumber"}, "carNumber like '%" + key + "%'", null, null, null, null);
        if (cursor.moveToFirst()) {
            String carNumber = cursor.getString(cursor.getColumnIndex("carNumber"));
            String status = cursor.getString(cursor.getColumnIndex("status"));
            String time = cursor.getString(cursor.getColumnIndex("time"));
            CarMaintainBean.CTNTBean ctntBean = new CarMaintainBean.CTNTBean();
            ctntBean.setCarNo(carNumber);
            ctntBean.setZt(status);
            ctntBean.setCzsj(time);
            strings.add(ctntBean);
        }
        cursor.close();
        return strings;
//        String sql = "select * from carTable where \"carNumber\" like '%" + key + "%';";
//        Cursor cursor = sqLiteDatabase.execu(sql);
//        Cursor cursor = sqLiteDatabase.rawQuery(s, new String[]{key});
//        if (cursor.moveToFirst()) {
//            strings.add(carNumber);
//        }
    }

    public void updateDatabase() {
        sqLiteDatabase = carDataHelper.getWritableDatabase();
        String sql = "DROP TABLE IF EXISTS " + CarTable.TABLE_NAME;
        sqLiteDatabase.execSQL(sql);
        String sql2 = "create table if not exists carTable1(_id integer primary key, carNumber varchar,status varchar,time varchar);";
        sqLiteDatabase.execSQL(sql2);
    }

    public void deleteDatabase() {
        sqLiteDatabase = carDataHelper.getWritableDatabase();
        sqLiteDatabase.delete("carTable", null, null);
    }

    public void colseDB() {
        sqLiteDatabase.close();
    }
}
