package com.example.controle_robo.dal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.controle_robo.db.migration.CreateResponsible;
import com.example.controle_robo.db.migration.CreateRobot;
import com.example.controle_robo.db.tables.ResponsibleTable;
import com.example.controle_robo.db.tables.RobotTable;

import java.util.ArrayList;
import java.util.List;

public class DalResponsible {
    private static final String TAG = "DAL_RES";

    private SQLiteDatabase db;
    private CreateResponsible database;

    public DalResponsible(Context context) {
        database = new CreateResponsible(context);
    }

    public boolean insert(String name) {
        ContentValues values;
        long result;

        db = database.getWritableDatabase();
        values = new ContentValues();
        values.put(ResponsibleTable.NAME, name);

        result = db.insert(ResponsibleTable.TABLE_NAME, null, values);
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

        result = db.delete(ResponsibleTable.TABLE_NAME, where, args);
        db.close();

        if(result == -1){
            Log.e(TAG, "delete: Erro deletando registro");
        }
        return  true;
    }

    public boolean update(int id, String name) {
        ContentValues values;
        long result;

        String where = "_id = ?";
        String[] args = { String.valueOf(id) };

        db = database.getWritableDatabase();

        values = new ContentValues();
        values.put(RobotTable.NAME, name);

        result = db.update(ResponsibleTable.TABLE_NAME, values, where, args);
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

        cursor = db.query(ResponsibleTable.TABLE_NAME, null,
                where, args, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        db.close();
        return cursor;
    }

    public Cursor loadAll() {
        Cursor cursor;
        String[] fields = ResponsibleTable.COLUMNS;
        db = database.getReadableDatabase();

        cursor = db.query(ResponsibleTable.TABLE_NAME, fields, null,
                null, null, null,
                null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        db.close();
        return cursor;
    }

    public List<String> getAllLabels(){
        List<String> list = new ArrayList<String>();

        String selectQuery = "SELECT  * FROM " + ResponsibleTable.TABLE_NAME;

        SQLiteDatabase db = database.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                list.add(cursor.getString(1));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return list;
    }

}
