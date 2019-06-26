package com.example.controle_robo.db.tables;

public class RelationTable {
    public static final String TABLE_NAME = "relation";
    public static final String PRIMARY_KEY = "_id";

    // Database Columns
    public static final String R_ID_ROBOT = "r_id_robot";
    public static final String R_ID_RESPONSIBLE = "r_id_responsible";
    public static final String R_ID_DESC= "r_desc";




    public static final String[] COLUMNS = {
            PRIMARY_KEY,
            R_ID_ROBOT,
            R_ID_DESC
    };
}
