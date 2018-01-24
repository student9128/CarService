package com.kevin.carservice.database;

import com.kevin.carservice.cardatadao.CarDataEntityDao;

/**
 * Created by <a href="http://blog.csdn.net/student9128">Kevin</a> on 2018/1/24.
 * <h3>Description:</h3>
 * <div>
 * </div>
 */


public class EntityManager {
    private static EntityManager entityManager;
    public CarDataEntityDao carDataEntityDao;

    public CarDataEntityDao getCarDataEntityDao() {
        carDataEntityDao = DaoManager.getInstance().getDaoSession().getCarDataEntityDao();
        return carDataEntityDao;
    }

    public static EntityManager getInstance() {
        if (entityManager == null) {
            entityManager = new EntityManager();
        }
        return entityManager;
    }
}
