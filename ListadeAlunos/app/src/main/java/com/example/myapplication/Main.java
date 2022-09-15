package com.example.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toast.makeText(this, "WARNING MESSAGE!\n" +
                " YOUR PHONE IS BEING HACKED!", Toast.LENGTH_LONG).show();
        setContentView(R.layout.activity_main);
        List<String> alunos = new ArrayList<>(Arrays.asList("Jose", "Rafael", "Ruan"));
        ListView list = findViewById(R.id.activity_main_lista_de_alunos);
        list.setAdapter(
                new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1,
                        alunos
                ));
    }

}
