package com.example.controle_robo.obj;

public class Categoria {
    int id;
    String name;

    public Categoria(int id, String nome) {
        this.id = id;
        this.name = nome;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

}
