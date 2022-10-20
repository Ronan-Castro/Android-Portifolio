package com.example.buscadadosporapi;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

/**
 * Created by allanromanato on 11/4/15.
 */
public class Utils {

    public List<Time> getInformacao(String end, List<Time> lista2){
        String json;
        json = NetworkUtils.getJSONFromAPI(end);
        Log.i("Resultado", json);
        lista2 = parseJson(json,lista2);
        return lista2;
    }

    private List<Time> parseJson(String json, List<Time> lista2){

        try {

            JSONArray array = new JSONArray(json);

            for(int i=0; i < array.length(); i++) {
                JSONObject objArray = array.getJSONObject(i);
                Time time = new Time();

                //Atribui os objetos que estão nas camadas mais altas
                time.setTime(objArray.getString("Time"));
                time.setAno(objArray.getInt("Ano"));
                time.setPosicao(objArray.getInt("Posicao"));
                time.setMediaValorMercado(objArray.getString("Media_Valor_Mercado"));
                time.setValorTotalMercado(objArray.getString("Valor_de_mercado_total"));
                lista2.add(time);
            }

            return lista2;
        }catch (JSONException e){
            e.printStackTrace();
            System.out.println(e);
            return null;
        }
    }

    private Bitmap baixarImagem(String url) {
        try{
            URL endereco;
            InputStream inputStream;
            Bitmap imagem; endereco = new URL(url);
            inputStream = endereco.openStream();
            imagem = BitmapFactory.decodeStream(inputStream);
            inputStream.close();
            return imagem;
        }catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}