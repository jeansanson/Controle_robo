package com.example.controle_robo.db.migration;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.controle_robo.db.Database;
import com.example.controle_robo.db.tables.RelationTable;
import com.example.controle_robo.db.tables.ResponsibleTable;
import com.example.controle_robo.db.tables.RobotTable;

public class CreateRelation extends SQLiteOpenHelper {

    private static final int VERSION = 1;

    public CreateRelation(Context context) {
        super(context, "db_robots", null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + RelationTable.TABLE_NAME + "( "
                + RelationTable.PRIMARY_KEY + " integer primary key autoincrement, "
                + RelationTable.R_ID_RESPONSIBLE+ " integer, "
                + RelationTable.R_ID_ROBOT+ " integer, "
                + RelationTable.R_ID_DESC+ "string "+
                ")";

        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + RelationTable.TABLE_NAME);
        onCreate(db);
    }
}
