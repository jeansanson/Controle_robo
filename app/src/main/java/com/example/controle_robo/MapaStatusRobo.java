package com.example.controle_robo;

import java.util.HashMap;
import java.util.Map;

public class MapaStatusRobo {

    private Map<Integer,String> status = new HashMap<Integer, String>();

    public Map loadStatus(){
        status.put(-1,"Indefinido");
        status.put(0,"Descartado");
        status.put(1,"Pronto");
        status.put(2,"Revisão pendente");
        status.put(3,"Necessita reparos");
        status.put(4,"Em produção");
        status.put(5,"Em manutenção");

        return status;
    }

    public Integer getKey(String status, Map statusMap){
        int aux=-1;
        for(int i=-1;i<statusMap.size();i++){
             if (status.compareTo(statusMap.get(i).toString())==0) {
                 aux=i;
             }
        }
        return aux;
    }
}
