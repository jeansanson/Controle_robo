package com.example.controle_robo;

import java.util.HashMap;
import java.util.Map;

public class MapaStatusRobo {

    public final Map<Integer,String> status = new HashMap<Integer, String>();

    public void loadStatus(){
        status.put(0,"Descartado");
        status.put(1,"Pronto");
        status.put(2,"Revisão pendente");
        status.put(3,"Necessita reparos");
        status.put(4,"Em produção");
        status.put(5,"Em manutenção");
    }
}
