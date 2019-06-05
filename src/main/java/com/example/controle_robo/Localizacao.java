package com.example.controle_robo;

public class Localizacao {
    int id;
    String cidade;

    public Localizacao(int id, String cidade) {
        this.id = id;
        this.cidade = cidade;
    }

    public int getId() {
        return id;
    }

    public String getCidade() {
        return cidade;
    }
}
