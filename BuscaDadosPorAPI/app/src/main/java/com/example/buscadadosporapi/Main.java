package com.example.buscadadosporapi;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class Main extends Activity {
        private ListView listaTimesView;
        private List<Time> lista;
        private adapterTime adapter;
        private ProgressDialog load;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.listatimes);
            listaTimesView = (ListView) findViewById((R.id.listViewTimes));

            
            lista = new ArrayList<Time>();
            adapter = new adapterTime(Main.this, lista);
            listaTimesView.setAdapter(adapter);
            GetJson download = new GetJson();

            //Chama Async Task
            download.execute();

        }

        @SuppressLint("StaticFieldLeak")
        private class GetJson extends AsyncTask<Void, Void, List<Time>> {

            @Override
            protected void onPreExecute(){
                load = ProgressDialog.show(Main.this,"Por favor Aguarde ...", "Recuperando Informações do Servidor...");
            }

            @Override
            protected List<Time> doInBackground(Void... params) {
                Utils util = new Utils();

                return util.getInformacao("https://192.168.11.12/transfermarket/read.php", lista);
            }

            @Override
            protected void onPostExecute(List<Time> lista){
                runOnUiThread(new Runnable() {
                    public void run() {
                        adapter.notifyDataSetChanged();
                    }
                });
                load.dismiss();
            }
        }
    }

