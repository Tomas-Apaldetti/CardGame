package com.core.g3.DataBase;

public class DataBaseContext {

    private static IDataBase db = new NullDataBase();

    public static void setInstance(IDataBase db) {
        DataBaseContext.db = db;
    }

    public static IDataBase getInstance() {
        return DataBaseContext.db;
    }
}
