package com.example.controle_robo;

import java.sql.Date;

public class Robo {
    int id;
    String nome;
    int status;
    String categoria;
    Date data_insercao;
    Date data_inicio;
    Date data_termino;
    Date data_inicio_previsao;
    Date data_termino_previsao;
    String responsavel;
    String localizacao;

    public Robo() {
    }

    public Robo(int id, String nome, int status, String categoria, Date data_insercao, Date data_inicio, Date data_termino, Date data_inicio_previsao, Date data_termino_previsao, String responsavel, String localizacao) {
        this.id = id;
        this.nome = nome;
        this.status = status;
        this.categoria = categoria;
        this.data_insercao = data_insercao;
        this.data_inicio = data_inicio;
        this.data_termino = data_termino;
        this.data_inicio_previsao = data_inicio_previsao;
        this.data_termino_previsao = data_termino_previsao;
        this.responsavel = responsavel;
        this.localizacao = localizacao;
    }
}
