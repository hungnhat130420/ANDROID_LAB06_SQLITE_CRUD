package com.example.lab06;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.List;

public class CustomAdapter extends BaseAdapter {
    private Context context;
    private int layoutRes;
    private List<String> list;
    private DatabaseHandler databaseHandler;

    public CustomAdapter(Context context, int layoutRes) {
        this.context = context;
        this.layoutRes = layoutRes;
        databaseHandler = new DatabaseHandler(context);
        this.list = databaseHandler.getAll();
    }
    public void add(String value){
        databaseHandler.addValue(value);
        list.add(value);
        notifyDataSetChanged();
    }

    public void remove(String value){
        databaseHandler.removeValue(value);
        list.remove(value);
        notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(layoutRes,parent,false);
        TextView tvName = convertView.findViewById(R.id.tvName);
        tvName.setText(list.get(position));
        return convertView;
    }
}
