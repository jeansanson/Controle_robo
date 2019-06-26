package com.example.controle_robo.dal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.controle_robo.db.migration.CreateRobot;
import com.example.controle_robo.db.tables.RobotTable;

public class DalRobot {
    private static final String TAG = "DAL_ROB";

    private SQLiteDatabase db;
    private CreateRobot database;

    public DalRobot(Context context) {
        database = new CreateRobot(context);
    }

    public boolean insert(String name, String category, String status, String local) {
        ContentValues values;
        long result;

        db = database.getWritableDatabase();
        values = new ContentValues();
        values.put(RobotTable.NAME, name);
        values.put(RobotTable.CATEGORY, category);
        values.put(RobotTable.STATUS, status);
        values.put(RobotTable.LOCAL, local);


        result = db.insert(RobotTable.TABLE_NAME, null, values);
        db.close();


        if (result == -1) {
            Log.e(TAG, "insert: Erro inserindo registro");
            return false;
        }

        return true;
    }

    public boolean deleteById(int id){

        String where = "_id = ?";
        String[] args = { String.valueOf(id) };
        db = database.getReadableDatabase();
        long result;

        result = db.delete(RobotTable.TABLE_NAME, where, args);
        db.close();

        if(result == -1){
            Log.e(TAG, "delete: Erro deletando registro");
        }
        return  true;
    }

    public boolean update(int id, String name, String category, String status, String local) {
        ContentValues values;
        long result;

        String where = "_id = ?";
        String[] args = { String.valueOf(id) };

        db = database.getWritableDatabase();

        values = new ContentValues();
        values.put(RobotTable.NAME, name);
        values.put(RobotTable.CATEGORY, category);
        values.put(RobotTable.STATUS, status);
        values.put(RobotTable.LOCAL, local);

        result = db.update(RobotTable.TABLE_NAME, values, where, args);
        db.close();


        if (result == -1) {
            Log.e(TAG, "insert: Erro atualizado registro");
            return false;
        }

        return true;
    }

    public Cursor findById(int id) {
        Cursor cursor;
        String where = "_id = ?";
        String[] args = { String.valueOf(id) };

        db = database.getReadableDatabase();

        cursor = db.query(RobotTable.TABLE_NAME, null,
                where, args, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        db.close();
        return cursor;
    }

    public Cursor loadAll() {
        Cursor cursor;
        String[] fields = RobotTable.COLUMNS;
        db = database.getReadableDatabase();

        cursor = db.query(RobotTable.TABLE_NAME, fields, null,
                null, null, null,
                null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        db.close();
        return cursor;
    }

}
