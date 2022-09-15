package com.example.myapplication.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.dao.AlunoDAO;
import com.example.myapplication.model.Aluno;

import java.util.List;



public class ViewAlunoActivity extends AppCompatActivity {

    private final AlunoDAO dao = new AlunoDAO();

    private int position;

    private TextView Nome;
    private TextView Telefone;
    private TextView Email;

    public static final String TITULO_APPBAR = "Aluno";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_abre_aluno);
        setTitle(TITULO_APPBAR);

        IniciandoViews();

        SetText();

        BotaoVoltar();
    }

    private void IniciandoViews() {
        Nome = (TextView) findViewById(R.id.activity_aluno_nome);
        Email = (TextView) findViewById(R.id.activity_aluno_email);
        Telefone = (TextView) findViewById(R.id.activity_aluno_telefone);
    }

    private void SetText() {
        Bundle extras = getIntent().getExtras();
        position = extras.getInt("position");

        List<Aluno> alunoList = dao.todos();
        String email = alunoList.get(position).getEmail();
        Nome.setText(email);
        String nome = alunoList.get(position).nome;
        Email.setText(nome);
        String telefone = alunoList.get(position).getTelefone();
        Telefone.setText(telefone);
    }

    private void BotaoVoltar() {
        Button botaoVoltar = findViewById((R.id.activity_aluno_voltar));
        botaoVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
