package com.example.controle_robo.db.migration;

import com.example.controle_robo.db.Database;
import com.example.controle_robo.db.tables.ResponsibleTable;
import com.example.controle_robo.db.tables.RobotTable;

public class CreateResponsible implements Database.DatabaseMigration{

        @Override
        public String up() {
            return "CREATE TABLE " + ResponsibleTable.TABLE_NAME + "( "
                    + ResponsibleTable.PRIMARY_KEY + " integer primary key autoincrement, "
                    + ResponsibleTable.COLUMN_NAME + " string, "+
                    ")";
        }

        @Override
        public String down() {
            return "DROP TABLE IF EXISTS " + ResponsibleTable.TABLE_NAME;
        }

}
