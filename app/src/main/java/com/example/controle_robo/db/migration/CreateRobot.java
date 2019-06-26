package com.example.controle_robo.db.migration;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.controle_robo.db.Database;
import com.example.controle_robo.db.tables.RobotTable;

public class CreateRobot extends SQLiteOpenHelper {


    private static final int VERSION = 1;

    public CreateRobot(Context context) {
        super(context, "db_robots", null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + RobotTable.TABLE_NAME + "( "
                + RobotTable.PRIMARY_KEY + " integer primary key autoincrement, "
                + RobotTable.NAME + " string, "
                + RobotTable.CATEGORY + " string, "
                + RobotTable.STATUS + " string, "
                + RobotTable.LOCAL+ " string "+
                ")";

        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + RobotTable.TABLE_NAME);
        onCreate(db);
    }
}
