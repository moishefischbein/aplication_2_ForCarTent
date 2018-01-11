package com.example.moish.aplication_2_forCarRent.model.backend;

import com.example.moish.carrentforcompany.model.datasource.MySQL_DBManager;

/**
 * Created by moish on 16/11/2017.
 */

public class DBManagerFactory {
    static DB_manager manager = null;

    public static DB_manager getManager() {
        if (manager == null)
            manager = new MySQL_DBManager();//List_DBManager();
        return manager;
    }

    private DBManagerFactory() {
    }
}
