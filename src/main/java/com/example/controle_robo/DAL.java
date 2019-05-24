package com.example.controle_robo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DAL {
    private static final String TAG = "DAL";

    private SQLiteDatabase db;
    private CreateDatabase database;

    public DAL(Context context) {
        database = new CreateDatabase(context);
    }

    public boolean insert(String nome, String categoria, Integer leucocitos, Integer glicemia, Integer asttgo, Integer ldh, Boolean litiaseBiliar, Integer pontuacao, Integer mortalidade) {
        ContentValues values;
        long result;

        db = database.getWritableDatabase();
        values = new ContentValues();
        values.put(CreateDatabase.NOME, nome);
        values.put(CreateDatabase.STATUS, status);
        values.put(CreateDatabase.CATEGORIA, categoria);
        values.put(CreateDatabase.DATA_INICIO, data_inicio);
        values.put(CreateDatabase.DATA_TERMINO, data_termino);
        values.put(CreateDatabase.DATA_INICIO_PREVISAO, data_inicio_previsao);
        values.put(CreateDatabase.DATA_TERMINO_PREVISAO, data_termino_previsao);
        values.put(CreateDatabase.RESPONSAVEL, responsavel);

        result = db.insert(CreateDatabase.TABLE, null, values);
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

        result = db.delete(CreateDatabase.TABLE, where, args);
        db.close();

        if(result == -1){
            Log.e(TAG, "delete: Erro deletando registro");
        }
        return  true;
    }

    public boolean update(int id, String nome, Integer idade, Integer leucocitos, Integer glicemia, Integer asttgo, Integer ldh, Boolean litiaseBiliar, Integer pontuacao, Integer mortalidade) {
        ContentValues values;
        long result;

        String where = "_id = ?";
        String[] args = { String.valueOf(id) };

        db = database.getWritableDatabase();

        values = new ContentValues();
        values.put(CreateDatabase.NOME, nome);
        values.put(CreateDatabase.IDADE, idade);
        values.put(CreateDatabase.LEUCOCITOS, leucocitos);
        values.put(CreateDatabase.GLICEMIA, glicemia);
        values.put(CreateDatabase.ASTTGO, asttgo);
        values.put(CreateDatabase.LDH, ldh);
        values.put(CreateDatabase.LITIASEBILIAR, litiaseBiliar);
        values.put(CreateDatabase.PONTUACAO, pontuacao);
        values.put(CreateDatabase.MORTALIDADE, mortalidade);

        result = db.update(CreateDatabase.TABLE, values, where, args);
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

        cursor = db.query(CreateDatabase.TABLE, null,
                where, args, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        db.close();
        return cursor;
    }

    public Cursor loadAll() {
        Cursor cursor;
        String[] fields = {CreateDatabase.ID, CreateDatabase.NOME, CreateDatabase.MORTALIDADE, CreateDatabase.IDADE};
        db = database.getReadableDatabase();

        // SELECT _id, title FROM book
        // String sql = "SELECT _id, title FROM book";
        //cursor = db.rawQuery(sql, null);
        cursor = db.query(CreateDatabase.TABLE, fields, null,
                null, null, null,
                null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        db.close();
        return cursor;
    }
}
