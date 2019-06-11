package com.example.controle_robo;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.controle_robo.obj.Robo;

import java.util.List;

public class RoboViewAdapter extends ArrayAdapter {

    private static final String TAG = "RoboViewAdapter";
    private int layoutResource;
    private LayoutInflater layoutInflater;
    private List<Robo> roboList;

    public RoboViewAdapter(Context context, int resource, List<Robo> roboList) {
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

        Robo roboAtual = roboList.get(position);

        viewHolder.tvRoboName.setText(roboAtual.getName());
        viewHolder.tvRoboId.setText(String.valueOf(roboAtual.getId()));
        viewHolder.tvRoboCategory.setText(roboAtual.getCategory());

        return convertView;
    }

    private class ViewHolder {
        final TextView tvRoboName;
        final TextView tvRoboId;
        final TextView tvRoboCategory;

        ViewHolder(View v) {
            this.tvRoboName = v.findViewById(R.id.robotName);
            this.tvRoboId = v.findViewById(R.id.robotId);
            this.tvRoboCategory = v.findViewById(R.id.robotCategory);
        }
    }
}
