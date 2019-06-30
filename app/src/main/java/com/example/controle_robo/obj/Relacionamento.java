package com.example.controle_robo.obj;

import java.io.Serializable;

public class Relacionamento  implements Serializable {

    int id;
    String robName;
    String robCategory;
    String status;
    String resName;
    String locCity;
    String description;

    public Relacionamento() {
    }

    public Relacionamento(int id, String robName, String robCategory, String status, String resName, String locCity, String description) {
        this.id = id;
        this.robName = robName;
        this.robCategory = robCategory;
        this.status = status;
        this.resName = resName;
        this.locCity = locCity;
        this.description = description;
    }

    public Relacionamento(int id, String rob_name, String rob_category) {
        this.id = id;
        this.robName = rob_name;
        this.robCategory = rob_category;
        this.status = "indefinido";
        this.resName="indefinido";
        this.locCity="indefinido";
        this.description="Descrição vazia";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRobName() {
        return robName;
    }

    public void setRobName(String robName) {
        this.robName = robName;
    }

    public String getRobCategory() {
        return robCategory;
    }

    public void setRobCategory(String robCategory) {
        this.robCategory = robCategory;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) { this.status = status; }

    public String getResName() {
        return resName;
    }

    public void setResName(String resName) {
        this.resName = resName;
    }

    public String getLocCity() {
        return locCity;
    }

    public void setLocCity(String locCity) {
        this.locCity = locCity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
