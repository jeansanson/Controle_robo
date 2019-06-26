package com.example.controle_robo.db.migration;

import com.example.controle_robo.db.Database;
import com.example.controle_robo.db.tables.CategoryTable;
import com.example.controle_robo.db.tables.LocalizationTable;

public class CreateLocalization implements Database.DatabaseMigration{
        @Override
        public String up() {
            return "CREATE TABLE " + LocalizationTable.TABLE_NAME + "( "
                    + LocalizationTable.PRIMARY_KEY + " integer primary key autoincrement, "+
                    ")";
        }

        @Override
        public String down() {
            return "DROP TABLE IF EXISTS " + LocalizationTable.TABLE_NAME;
        }
}
