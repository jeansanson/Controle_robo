package com.example.controle_robo;

public class Responsavel {
    int id;
    String nome;

    public Responsavel(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }
}
