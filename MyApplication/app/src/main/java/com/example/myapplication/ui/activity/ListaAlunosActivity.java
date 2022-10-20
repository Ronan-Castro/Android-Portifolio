package com.example.myapplication.ui.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;


import com.example.myapplication.R;
import com.example.myapplication.dao.AlunoDAO;
import com.example.myapplication.model.Aluno;
import com.example.myapplication.ui.adapter.ListaAlunosAdapter;

import java.util.ArrayList;
import java.util.List;


public class ListaAlunosActivity extends AppCompatActivity {
    private final AlunoDAO dao = new AlunoDAO();
    public static final String TITULO_APPBAR = "Lista de Alunos";
    public static final String CHAVE_ALUNO = "aluno";
    private ListaAlunosAdapter adapter;


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

        AtualizaAlunos();

        ConfiguraNovoAluno();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.activity_lista_alunos_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        Aluno alunoEscolhido = adapter.getItem(menuInfo.position);
        CharSequence title = item.getTitle();
        if ("Remover".equals(title)) {
            ConfirmaRemocao(alunoEscolhido);
        } else if ("Editar".equals(title)) {
            AbreFormularioModoEditaAluno(alunoEscolhido);
        }
        return super.onContextItemSelected(item);
    }

    private void ConfirmaRemocao(Aluno alunoEscolhido) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Removendo Aluno");
        builder.setMessage("Tem certeza que deseja remover o aluno?");
        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                RemoveAluno(alunoEscolhido);
            }
        });
        builder.setNegativeButton("Não", null);
        builder.show();
    }

    private void AtualizaAlunos() {
        adapter.atualiza(dao.todos());
    }

    private void ConfiguraLista() {
        List<Aluno> alunos = dao.todos();
        ListView list = findViewById(R.id.activity_lista_de_alunos_listview);
        ConfiguraAdapter(list);
        ConfiguraListenerPorItem(list);
        registerForContextMenu(list);
    }

    private void RemoveAluno(Aluno alunoEscolhido) {
        dao.remove(alunoEscolhido);
        adapter.remove(alunoEscolhido);
    }

    private void ConfiguraListenerPorItem(ListView list) {
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view,
                                    int position, long id) {
                AbreAluno(position);
            }
        });
    }

    private void AbreFormularioModoEditaAluno(Aluno alunoEscolhido) {
        Intent VaiParaFormularioActivity = new Intent(ListaAlunosActivity.this, FormularioAlunosActivity.class);
        VaiParaFormularioActivity.putExtra(CHAVE_ALUNO, alunoEscolhido);
        startActivity(VaiParaFormularioActivity);
    }

    private void ConfiguraAdapter(ListView list) {
        adapter = new ListaAlunosAdapter(this);
        list.setAdapter(adapter);
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
        Intent intent = new Intent(ListaAlunosActivity.this, FormularioAlunosActivity.class);
        startActivity(intent);
    }

    private void AbreAluno(int position) {
        Intent intent = new Intent(ListaAlunosActivity.this, ViewAlunoActivity.class);
        startActivity(intent.putExtra("position", position));
    }
}
