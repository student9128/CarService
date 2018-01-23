package com.kevin.carservice.database;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.kevin.carservice.bean.CarMaintainBean;
import com.kevin.carservice.utils.LogK;

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
    private static final String TAG = "CarDao.class";

    public CarDao() {
        carDataHelper = CarDataHelper.getInstance();
    }

    public void insert(String carNumber, String status, String time, String color) {
        sqLiteDatabase = carDataHelper.getWritableDatabase();
        String sql = "insert into " + CarTable.TABLE_NAME + "(" + CarTable.COLUMN_CAR_NUMBER + ","
                + CarTable.COLUMN_CAR_STATUS + ","
                + CarTable.COLUMN_CAR_TIME + ","
                + CarTable.COLUMN_STATUS_COLOR + ") values (?,?,?,?)";
        sqLiteDatabase.execSQL(sql, new String[]{carNumber, status, time, color});
    }

    public void queryByKey(String key) {
        sqLiteDatabase = carDataHelper.getWritableDatabase();
        String sql = "SELECT * FROM " + CarTable.TABLE_NAME + " WHERE " + CarTable.COLUMN_CAR_NUMBER + " LIKE " + key;
        sqLiteDatabase.execSQL(sql, new String[]{"%" + key + "%"});
    }

    public List<CarMaintainBean.CTNTBean> queryByKeyLetter(List<CarMaintainBean.CTNTBean> strings, String key) {
        strings.clear();
        sqLiteDatabase = carDataHelper.getWritableDatabase();
        String carTable = "carTable";
        String sql = "select * from "
                + CarTable.TABLE_NAME
                + " where carNumber like ?";
        Cursor cursor = sqLiteDatabase.rawQuery(sql, new String[]{"%" + key + "%"});
        cursor.moveToFirst();
        while (cursor.moveToNext()) {
            String carNumber = cursor.getString(cursor.getColumnIndex(CarTable.COLUMN_CAR_NUMBER));
            String status = cursor.getString(cursor.getColumnIndex(CarTable.COLUMN_CAR_STATUS));
            String createTime = cursor.getString(cursor.getColumnIndex(CarTable.COLUMN_CAR_TIME));
            String statusColor = cursor.getString(cursor.getColumnIndex(CarTable.COLUMN_STATUS_COLOR));
            LogK.d(TAG, "carNumber:\t" + carNumber);
            LogK.d(TAG, "status:\t" + status);
            LogK.d(TAG, "createTime:\t" + createTime);
            LogK.d(TAG, "statusColor:\t" + statusColor);
            CarMaintainBean.CTNTBean ctntBean = new CarMaintainBean.CTNTBean();
            ctntBean.setCarNo(carNumber);
            ctntBean.setZt(status);
            ctntBean.setCzsj(createTime);
            ctntBean.setColor(statusColor);
            strings.add(ctntBean);
        }

//        cursor.close();
        return strings;
    }


    /**
     * 清空数据库
     */
    public void deleteDatabase() {
        sqLiteDatabase = carDataHelper.getWritableDatabase();
        String sql = "delete from " + CarTable.TABLE_NAME;
        sqLiteDatabase.execSQL(sql);
    }

    public void colseDB() {
        sqLiteDatabase.close();
    }
}
