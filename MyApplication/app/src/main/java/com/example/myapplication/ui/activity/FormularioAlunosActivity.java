package com.example.myapplication.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.myapplication.R;
import com.example.myapplication.dao.AlunoDAO;
import com.example.myapplication.model.Aluno;

public class FormularioAlunosActivity extends AppCompatActivity {

    public static final String TITULO_APPBAR = "Novo aluno";
    private EditText Nome;
    private EditText Telefone;
    private EditText Email;
    private final AlunoDAO dao = new AlunoDAO();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_alunos);
        setTitle(TITULO_APPBAR);


        inicializandoCampos();

        BotaoSalvar();
    }

    private void BotaoSalvar() {
        Button botaoSalvar = findViewById((R.id.activity_formulario_alunos_salvar));
        botaoSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Aluno alunoCriado = criaAluno();

                salva(alunoCriado);
            }
        });
    }

    private void inicializandoCampos() {
        Nome = findViewById(R.id.activity_formulario_alunos_nome);
        Telefone = findViewById(R.id.activity_formulario_alunos_telefone);
        Email = findViewById(R.id.activity_formulario_alunos_email);
    }

    private void salva(Aluno alunoCriado) {
        dao.salva(alunoCriado);

        finish();
    }

    private Aluno criaAluno() {
        String nome = Nome.getText().toString();
        String telefone = Telefone.getText().toString();
        String email = Email.getText().toString();

        return new Aluno(nome, telefone, email);
    }
}