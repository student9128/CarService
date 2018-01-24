package com.kevin.carservice.database;

import com.kevin.carservice.base.BaseApplication;
import com.kevin.carservice.cardatadao.DaoMaster;
import com.kevin.carservice.cardatadao.DaoSession;

/**
 * Created by <a href="http://blog.csdn.net/student9128">Kevin</a> on 2018/1/24.
 * <h3>Description:</h3>
 * <div>
 * </div>
 */


public class DaoManager {
    private static DaoManager INSTANCE;
    private DaoMaster daoMaster;
    private DaoSession daoSession;

    private DaoManager() {
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(BaseApplication.getContext(),
                CarTable.DB_NAME, null);
        daoMaster = new DaoMaster(devOpenHelper.getWritableDb());
        daoSession = daoMaster.newSession();
    }

    public DaoMaster getDaoMaster() {
        return daoMaster;
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }

    public static DaoManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DaoManager();
        }
        return INSTANCE;
    }
}
