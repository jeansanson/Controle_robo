package com.example.controle_robo.db.tables;

public class RobotTable {
    public static final String TABLE_NAME = "robot";
    public static final String PRIMARY_KEY = "_id";

    // Database Columns
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_CATEGORY = "category";


    public static final String[] COLUMNS = {
            PRIMARY_KEY,
            COLUMN_NAME,
            COLUMN_CATEGORY
    };

}
