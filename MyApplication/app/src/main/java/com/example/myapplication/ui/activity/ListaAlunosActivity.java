package com.example.myapplication.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.example.myapplication.R;
import com.example.myapplication.dao.AlunoDAO;


public class ListaAlunosActivity extends AppCompatActivity {
    private final AlunoDAO dao = new AlunoDAO();
    public static final String TITULO_APPBAR = "Lista de Alunos";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);
        setTitle(TITULO_APPBAR);

        ConfiguraLista();

        ConfiguraNovoAluno();
    }

    @Override
    protected void onResume() {
        super.onResume();

        ConfiguraLista();

        ConfiguraNovoAluno();
    }

    private void ConfiguraLista() {
        ListView list = findViewById(R.id.activity_lista_de_alunos_listview);
        list.setAdapter(
                new ArrayAdapter<>(
                        this,
                        android.R.layout.simple_list_item_1,
                        dao.todos()));
    }

    private void ConfiguraNovoAluno() {
        FloatingActionButton botaoAdd = findViewById((R.id.activity_lista_alunos_fab_novo_aluno));
        botaoAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AbreFormularioNovoAluno();
            }
        });
    }

    private void AbreFormularioNovoAluno() {
        startActivity(new Intent(ListaAlunosActivity.this,
                FormularioAlunosActivity.class));
    }
}
