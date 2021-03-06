package com.example.controle_robo.db.migration;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.controle_robo.db.Database;
import com.example.controle_robo.db.tables.ResponsibleTable;
import com.example.controle_robo.db.tables.RobotTable;

public class CreateResponsible extends SQLiteOpenHelper {

    private static final int VERSION = 1;

    public CreateResponsible(Context context) {
        super(context, "db_robots", null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + ResponsibleTable.TABLE_NAME + "( "
                + ResponsibleTable.PRIMARY_KEY + " integer primary key autoincrement, "
                + ResponsibleTable.NAME + " string "+
                ")";

        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + ResponsibleTable.TABLE_NAME);
        onCreate(db);
    }

}
