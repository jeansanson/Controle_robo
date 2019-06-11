package com.example.controle_robo.obj;

import java.sql.Date;

public class Robo {
    int id;
    String name;
    String category;

    public Robo(int id, String name, String category) {
        this.id = id;
        this.name = name;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return name;
    }
}
