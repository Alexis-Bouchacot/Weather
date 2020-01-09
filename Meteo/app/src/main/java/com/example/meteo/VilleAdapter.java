package com.example.meteo;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class VilleAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<Ville> mVilleList;

    public VilleAdapter(Context mContext, ArrayList<Ville> mProductList) {
        this.mContext = mContext;
        this.mVilleList = mProductList;
    }

    @Override
    public int getCount() {
        return mVilleList.size();
    }

    @Override
    public Object getItem(int position) {
        return mVilleList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = View.inflate(mContext, R.layout.ville_item, null);
        TextView nomVilleFavoris = (TextView)v.findViewById(R.id.nomVilleFavoris);
        TextView descVilleFavoris = (TextView)v.findViewById(R.id.descVilleFavoris);
        TextView tempVilleFavoris = (TextView)v.findViewById(R.id.tempVilleFavoris);

        nomVilleFavoris.setText(mVilleList.get(position).getNom());
        descVilleFavoris.setText(mVilleList.get(position).getDescription());
        tempVilleFavoris.setText(mVilleList.get(position).getTemp().get(0));

        return v;
    }
}