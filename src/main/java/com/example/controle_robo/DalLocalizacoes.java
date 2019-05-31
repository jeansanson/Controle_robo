package com.example.controle_robo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DalLocalizacoes {
    private static final String TAG = "Dal Localizações";

    private SQLiteDatabase db;
    private CreateDatabase database;

    public DalLocalizacoes(Context context) {
        database = new CreateDatabase(context);
    }

    public boolean insert(
            String nome
    ) {
        ContentValues values;
        long result;

        db = database.getWritableDatabase();
        values = new ContentValues();
        values.put(CreateDatabase.NOME, nome);


        result = db.insert(CreateDatabase.LOCALIZACOES, null, values);
        db.close();


        if (result == -1) {
            Log.e(TAG, "insert: Erro inserindo registro");
            return false;
        }

        return true;
    }

    public boolean deleteById(int id) {

        String where = "_id = ?";
        String[] args = {String.valueOf(id)};
        db = database.getReadableDatabase();
        long result;

        result = db.delete(CreateDatabase.LOCALIZACOES, where, args);
        db.close();

        if (result == -1) {
            Log.e(TAG, "delete: Erro deletando registro");
        }
        return true;
    }

    public boolean update(
            Integer id,
            String nome
    ) {
        ContentValues values;
        long result;

        String where = "_id = ?";
        String[] args = {String.valueOf(id)};

        db = database.getWritableDatabase();

        values = new ContentValues();
        values.put(CreateDatabase.NOME, nome);

        result = db.update(CreateDatabase.LOCALIZACOES, values, where, args);
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
        String[] args = {String.valueOf(id)};

        db = database.getReadableDatabase();

        cursor = db.query(CreateDatabase.LOCALIZACOES, null,
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

        cursor = db.query(CreateDatabase.LOCALIZACOES, fields, null,
                null, null, null,
                null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        db.close();
        return cursor;
    }
}
