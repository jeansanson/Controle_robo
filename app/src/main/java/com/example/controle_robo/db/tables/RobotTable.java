package com.example.controle_robo.db.tables;

public class RobotTable {
    public static final String TABLE_NAME = "robot";
    public static final String PRIMARY_KEY = "_id";

    // Database Columns
    public static final String NAME = "name";
    public static final String CATEGORY = "category";
    public static final String STATUS = "status";
    public static final String LOCAL = "status";


    public static final String[] COLUMNS = {
            PRIMARY_KEY,
            NAME,
            STATUS,
            CATEGORY,
            LOCAL
    };

}
