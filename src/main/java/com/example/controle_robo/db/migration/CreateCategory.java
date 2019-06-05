package com.example.controle_robo.db.migration;

import com.example.controle_robo.db.Database;
import com.example.controle_robo.db.tables.CategoryTable;
import com.example.controle_robo.db.tables.ResponsibleTable;

public class CreateCategory implements Database.DatabaseMigration{
    @Override
    public String up() {
        return "CREATE TABLE " + CategoryTable.TABLE_NAME + "( "
                + CategoryTable.PRIMARY_KEY + " integer primary key autoincrement, "
                + CategoryTable.COLUMN_NAME + " string, "+
                ")";
    }

    @Override
    public String down() {
        return "DROP TABLE IF EXISTS " + CategoryTable.TABLE_NAME;
    }

}
