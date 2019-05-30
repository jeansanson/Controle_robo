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
            Integer status,
            String categoria,
            Date data_insercao,
            Date data_inicio,
            Date data_termino,
            Date data_inicio_previsao,
            Date data_termino_previsao,
            String responsavel,
            String localizacao
    ) {
        ContentValues values;
        long result;

        db = database.getWritableDatabase();
        values = new ContentValues();
        values.put(CreateDatabase.NOME, nome);
        values.put(CreateDatabase.STATUS, status);
        values.put(CreateDatabase.CATEGORIA, categoria);
        values.put(CreateDatabase.DATA_INSERCAO, String.valueOf(data_insercao));
        values.put(CreateDatabase.DATA_INICIO, String.valueOf(data_inicio));
        values.put(CreateDatabase.DATA_TERMINO, String.valueOf(data_termino));
        values.put(CreateDatabase.DATA_INICIO_PREVISAO, String.valueOf(data_inicio_previsao));
        values.put(CreateDatabase.DATA_TERMINO_PREVISAO, String.valueOf(data_termino_previsao));
        values.put(CreateDatabase.RESPONSAVEL, responsavel);
        values.put(CreateDatabase.LOCALIZACAO, localizacao);

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
            Integer status,
            String categoria,
            Date data_insercao,
            Date data_inicio,
            Date data_termino,
            Date data_inicio_previsao,
            Date data_termino_previsao,
            String responsavel,
            String localizacao)
    {
        ContentValues values;
        long result;

        String where = "_id = ?";
        String[] args = { String.valueOf(id) };

        db = database.getWritableDatabase();

        values = new ContentValues();
        values.put(CreateDatabase.NOME, nome);
        values.put(CreateDatabase.STATUS, status);
        values.put(CreateDatabase.CATEGORIA, categoria);
        values.put(CreateDatabase.DATA_INSERCAO, String.valueOf(data_insercao));
        values.put(CreateDatabase.DATA_INICIO, String.valueOf(data_inicio));
        values.put(CreateDatabase.DATA_TERMINO, String.valueOf(data_termino));
        values.put(CreateDatabase.DATA_INICIO_PREVISAO, String.valueOf(data_inicio_previsao));
        values.put(CreateDatabase.DATA_TERMINO_PREVISAO, String.valueOf(data_termino_previsao));
        values.put(CreateDatabase.RESPONSAVEL, responsavel);
        values.put(CreateDatabase.LOCALIZACAO, localizacao);

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
