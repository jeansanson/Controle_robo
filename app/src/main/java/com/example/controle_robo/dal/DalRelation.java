package com.example.controle_robo.dal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.controle_robo.db.migration.CreateRelation;
import com.example.controle_robo.db.tables.RelationTable;
import com.example.controle_robo.db.tables.ResponsibleTable;

public class DalRelation {
    private static final String TAG = "DAL_RES";

    private SQLiteDatabase db;
    private CreateRelation database;

    public DalRelation(Context context) {
        database = new CreateRelation(context);
    }

    public boolean insert(Integer idRobot, Integer idResponsible, String description) {
        ContentValues values;
        long result;

        db = database.getWritableDatabase();
        values = new ContentValues();
        values.put(RelationTable.R_ID_ROBOT, idRobot);
        values.put(RelationTable.R_ID_RESPONSIBLE, idResponsible);
        values.put(RelationTable.R_ID_DESC, description);

        result = db.insert(RelationTable.TABLE_NAME, null, values);
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

    public boolean update(int id, Integer idRobot, Integer idResponsible, String description) {
        ContentValues values;
        long result;

        String where = "_id = ?";
        String[] args = { String.valueOf(id) };

        db = database.getWritableDatabase();

        values = new ContentValues();
        values.put(RelationTable.R_ID_ROBOT, idRobot);
        values.put(RelationTable.R_ID_RESPONSIBLE, idResponsible);
        values.put(RelationTable.R_ID_DESC, description);

        result = db.update(RelationTable.TABLE_NAME, values, where, args);
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

        cursor = db.query(RelationTable.TABLE_NAME, null,
                where, args, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        db.close();
        return cursor;
    }

    public Cursor loadAll() {
        Cursor cursor;
        String[] fields = RelationTable.COLUMNS;
        db = database.getReadableDatabase();

        cursor = db.query(RelationTable.TABLE_NAME, fields, null,
                null, null, null,
                null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        db.close();
        return cursor;
    }
}
