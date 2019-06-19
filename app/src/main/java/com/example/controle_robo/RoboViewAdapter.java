package com.example.controle_robo;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.controle_robo.obj.Relacionamento;
import com.example.controle_robo.obj.Robo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RoboViewAdapter extends ArrayAdapter {

    private static final String TAG = "RoboViewAdapter";
    private int layoutResource;
    private LayoutInflater layoutInflater;
    private List<Relacionamento> roboList;
    private Map<Integer,String> statusMap;

    public RoboViewAdapter(Context context, int resource, List<Relacionamento> roboList) {
        super(context, resource);
        this.layoutResource = resource;
        this.layoutInflater = LayoutInflater.from(context);
        this.roboList = roboList;
    }

    @Override
    public int getCount() {
        return roboList.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.d(TAG, "getView: ");
        ViewHolder viewHolder;

        if (convertView == null) {
            Log.d(TAG, "getView: chamada com um convertView null");
            convertView = layoutInflater.inflate(layoutResource, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            Log.d(TAG, "getView: recebeu um convertView");
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Relacionamento roboAtual = roboList.get(position);

        MapaStatusRobo map = new MapaStatusRobo();
        statusMap = new HashMap<>();
        statusMap = map.loadStatus();

        viewHolder.tvRoboName.setText(roboAtual.getRobName());
        viewHolder.tvRoboId.setText(String.valueOf(roboAtual.getId()));
        viewHolder.tvRoboCategory.setText(roboAtual.getRobCategory());
        viewHolder.tvRoboStatus.setText(statusMap.get(roboAtual.getStatus()));
        viewHolder.tvRoboResponsible.setText(roboAtual.getResName());

        return convertView;
    }

    private class ViewHolder {
        final TextView tvRoboName;
        final TextView tvRoboId;
        final TextView tvRoboCategory;
        final TextView tvRoboStatus;
        final TextView tvRoboResponsible;

        ViewHolder(View v) {
            this.tvRoboName = v.findViewById(R.id.robotName);
            this.tvRoboId = v.findViewById(R.id.robotId);
            this.tvRoboCategory = v.findViewById(R.id.robotCategory);
            this.tvRoboStatus = v.findViewById(R.id.robotStatus);
            this.tvRoboResponsible = v.findViewById(R.id.robotResponsible);
        }
    }
}
