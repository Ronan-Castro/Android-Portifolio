package com.example.buscadadosporapi;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class adapterTime extends BaseAdapter {
    private Context ctx;
    private List<Time> lista;

    public adapterTime(Context ctx2, List<Time> lista2) {
        ctx = ctx2;
        lista = lista2;

    }

    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Time getItem(int i) {
        return lista.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = null;

        if(view == null){
            LayoutInflater layoutInflater = ((Activity)ctx).getLayoutInflater();
            v = layoutInflater.inflate(R.layout.item_lista, null);
        }else{
            v = view;
        }

        Time t = new Time();
        t = this.getItem(i);


        TextView itemTime = (TextView) v.findViewById((R.id.time));
        TextView itemPosicao = (TextView) v.findViewById((R.id.posicao));
        TextView itemMedio = (TextView) v.findViewById((R.id.valormedio));
        TextView itemTotal = (TextView) v.findViewById((R.id.valortotal));
        TextView itemAno = (TextView) v.findViewById((R.id.ano));

        itemTime.setText("Time: " + t.getTime());
        itemPosicao.setText("Posição: " + String.valueOf(t.getPosicao()));
        itemMedio.setText("Média de valor dos jogadores: " + t.getMediaValorMercado());
        itemTotal.setText("Valor total da equipe: " + t.getValorTotalMercado());
        itemAno.setText("Ano: " + String.valueOf(t.getAno()));

        return v;
    }
}
