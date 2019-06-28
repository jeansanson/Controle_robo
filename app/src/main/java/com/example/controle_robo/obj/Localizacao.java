package com.example.controle_robo.obj;

public class Localizacao {
    int id;
    String city;

    public Localizacao(int id, String cidade) {
        this.id = id;
        this.city = cidade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
