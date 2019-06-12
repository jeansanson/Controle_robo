package com.example.controle_robo.obj;

public class Relacionamento {

    int id;
    String rob_name;
    String rob_category;
    int status;
    String res_name;
    String loc_city;
    String description;

    public Relacionamento() {
    }

    public Relacionamento(int id, String rob_name, String rob_category) {
        this.id = id;
        this.rob_name = rob_name;
        this.rob_category = rob_category;
    }

    public int getId() {
        return id;
    }

    public String getRob_name() {
        return rob_name;
    }

    public String getRob_category() {
        return rob_category;
    }

    public int getStatus() {
        return status;
    }

    public String getRes_name() {
        return res_name;
    }

    public String getLoc_city() {
        return loc_city;
    }

    public String getDescription() {
        return description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setRob_name(String rob_name) {
        this.rob_name = rob_name;
    }

    public void setRob_category(String rob_category) {
        this.rob_category = rob_category;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setRes_name(String res_name) {
        this.res_name = res_name;
    }

    public void setLoc_city(String loc_city) {
        this.loc_city = loc_city;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
