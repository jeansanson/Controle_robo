package com.example.controle_robo.db.migration;

import com.example.controle_robo.db.Database;
import com.example.controle_robo.db.tables.RobotTable;

public class CreateRobot implements Database.DatabaseMigration{
    @Override
    public String up() {
        return "CREATE TABLE " + RobotTable.TABLE_NAME + "( "
                + RobotTable.PRIMARY_KEY + " integer primary key autoincrement, "
                + RobotTable.COLUMN_NAME + " string, "
                + RobotTable.COLUMN_CATEGORY+ " string "+
                ")";
    }

    @Override
    public String down() {
        return "DROP TABLE IF EXISTS " + RobotTable.TABLE_NAME;
    }
}
