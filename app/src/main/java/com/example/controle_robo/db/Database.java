package com.example.controle_robo.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.controle_robo.db.migration.CreateRobot;

import java.util.HashMap;

public class Database {/*
    public static final String TAG = "Database";
    public static final String DB_NAME = "wickedbotz_robos.db";
    public static final int CURRENT_VERSION = 1;

    // <The migration interface, The version of database>
    private HashMap<DatabaseMigration, Integer> migrations;

    public Database(Context context) {
        super(context, DB_NAME, null, CURRENT_VERSION);

        migrations = new HashMap<>();
        migrations.put(new CreateRobot(), 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        for (DatabaseMigration m : migrations.keySet()) {
            Log.d(TAG, "----> Migration: " + m.up());
            db.execSQL(m.up());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        for (DatabaseMigration m : migrations.keySet()) {
            if (newVersion == migrations.get(m)) {
                Log.d(TAG, "----> Migration: " + m.up());
                db.execSQL(m.up());
            }
        }
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onDowngrade(db, oldVersion, newVersion);

        Log.w(TAG, "Upgrading database from version " + oldVersion + " to " + newVersion);
        for (DatabaseMigration m : migrations.keySet()) {
            if (oldVersion == migrations.get(m)) {
                Log.d(TAG, "----> Migration: " + m.down());
                db.execSQL(m.down());
            }
        }
    }

    public interface DatabaseMigration {
        String up();
        String down();
   }*/
}
