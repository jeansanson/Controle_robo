package com.example.controle_robo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CreateDatabase extends SQLiteOpenHelper {

    public static final String DB_NAME = "controle_robos.db";
    public static final String TABLE = "robos";
    public static final String ID = "_id";
    public static final String NOME = "nome";
    public static final String STATUS = "status";
    public static final String CATEGORIA = "categoria";
    public static final String DATA_INSERCAO = "data_insercao";
    public static final String DATA_INICIO = "data_inicio";
    public static final String DATA_TERMINO = "data_termino";
    public static final String DATA_INICIO_PREVISAO = "data_inicio_previsao";
    public static final String DATA_TERMINO_PREVISAO = "data_termino_previsao";
    public static final String RESPONSAVEL = "responsavel";
    public static final String LOCALIZACAO = "localizacao";

    private static final int VERSION = 1;

    public CreateDatabase(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE + " ( "
                + "_id integer primary key autoincrement, " +
                "nome text not null, " +
                "status tinyint not null, " +
                "categoria text not null, " +
                "data_insercao date not null, "+
                "data_inicio date, " +
                "data_termino date, " +
                "data_inicio_previsao date, " +
                "data_termino_previsao date, " +
                "responsavel text, " +
                "localizacao text " +
        ")";

        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE);
        onCreate(db);
    }
}
