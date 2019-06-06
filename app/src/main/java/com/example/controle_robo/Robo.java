package com.example.controle_robo;

import java.sql.Date;

public class Robo {
    int id;
    String nome;
    String categoria;

    public Robo() {
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCategoria() {
        return categoria;
    }

    public Robo(int id, String nome, String categoria) {
        this.id = id;
        this.nome = nome;
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return nome;
    }
}
