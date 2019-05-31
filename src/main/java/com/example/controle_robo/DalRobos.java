package com.example.controle_robo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.sql.Date;

public class DalRobos {
    private static final String TAG = "Dal Robo";

    private SQLiteDatabase db;
    private CreateDatabase database;

    public DalRobos(Context context) {
        database = new CreateDatabase(context);
    }

    public boolean insert(
            String nome,
            String categoria
    ) {
        ContentValues values;
        long result;

        db = database.getWritableDatabase();
        values = new ContentValues();
        values.put(CreateDatabase.NOME, nome);
        values.put(CreateDatabase.CATEGORIA, categoria);

        result = db.insert(CreateDatabase.ROBOS, null, values);
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

        result = db.delete(CreateDatabase.ROBOS, where, args);
        db.close();

        if(result == -1){
            Log.e(TAG, "delete: Erro deletando registro");
        }
        return  true;
    }

    public boolean update(
            Integer id,
            String nome,
            String categoria
    ) {
        ContentValues values;
        long result;

        String where = "_id = ?";
        String[] args = { String.valueOf(id) };

        db = database.getWritableDatabase();

        values = new ContentValues();
        values.put(CreateDatabase.NOME, nome);
        values.put(CreateDatabase.CATEGORIA, categoria);

        result = db.update(CreateDatabase.ROBOS, values, where, args);
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

        cursor = db.query(CreateDatabase.ROBOS, null,
                where, args, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        db.close();
        return cursor;
    }

    public Cursor loadAll() {
        Cursor cursor;
        String[] fields = {CreateDatabase.ID, CreateDatabase.NOME};
        db = database.getReadableDatabase();

        cursor = db.query(CreateDatabase.ROBOS, fields, null,
                null, null, null,
                null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        db.close();
        return cursor;
    }
}
