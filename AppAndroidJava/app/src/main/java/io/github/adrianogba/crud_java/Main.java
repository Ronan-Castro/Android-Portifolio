package io.github.adrianogba.crud_java;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import io.github.adrianogba.crud_java.adapter.VeiculoListAdapter;
import io.github.adrianogba.crud_java.model.Veiculo;

public class Main extends AppCompatActivity {

    ListView veiculosListView;
    ProgressDialog progressDialog;
    SwipeRefreshLayout swipeRefresh;
    ArrayList<Veiculo> veiculosList;
    TextView errormessage;
    RequestQueue queue;

    private JsonParser jsonParser;
    private Gson gson;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        veiculosListView = findViewById(R.id.veiculosListView);
        swipeRefresh = findViewById(R.id.swipeRefresh);
        veiculosList = new ArrayList<>();
        errormessage = findViewById(R.id.errormessage);

        jsonParser = new JsonParser();
        gson = new Gson();

        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                carregarLista();

            }
        });

        findViewById(R.id.btnAddVeiculo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), AddVeiculo.class);
                v.getContext().startActivity(i);
            }
        });


        progressDialog = new ProgressDialog(Main.this);
        progressDialog.setMessage("Carregando...");
        progressDialog.setCancelable(false);
        progressDialog.show();


        queue = Volley.newRequestQueue(this);

        carregarLista();

    }


    public void carregarLista(){

        veiculosList.clear();
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="https://www.metaweather.com/api/location/search/?query=london";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(Main.this, response, Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Main.this, "error", Toast.LENGTH_SHORT).show();
            }});

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

}
