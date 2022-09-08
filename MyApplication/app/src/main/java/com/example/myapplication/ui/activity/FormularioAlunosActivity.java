package com.example.myapplication.ui.activity;

import static com.example.myapplication.ui.activity.ListaAlunosActivity.CHAVE_ALUNO;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.myapplication.R;
import com.example.myapplication.dao.AlunoDAO;
import com.example.myapplication.model.Aluno;

public class FormularioAlunosActivity extends AppCompatActivity {

    public static final String TITULO_APPBAR_NOVO = "Novo aluno";
    public static final String TITULO_APPBAR_EDITA = "Edita aluno";
    private EditText Nome;
    private EditText Telefone;
    private EditText Email;
    private final AlunoDAO dao = new AlunoDAO();
    private Aluno aluno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_alunos);
        inicializandoCampos();
        CarregaAluno();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_fromulario_alunos_salvar_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.activity_formulario_alunos_salvar){
            FinalizaFormulario();
        }
        return super.onOptionsItemSelected(item);
    }

    private void CarregaAluno() {
        Intent dados = getIntent();
        if(dados.hasExtra((CHAVE_ALUNO))) { // editando aluno
            setTitle(TITULO_APPBAR_EDITA);
            aluno = (Aluno) dados.getSerializableExtra("aluno");
            PreencheCampos();
        } else{ // novo aluno
            setTitle(TITULO_APPBAR_NOVO);
            aluno = new Aluno();
        }
    }

    private void PreencheCampos() {
        Nome.setText(aluno.nome);
        Telefone.setText(aluno.getTelefone());
        Email.setText(aluno.getEmail());
    }

    private void BotaoSalvar() {
        Button botaoSalvar = findViewById((R.id.activity_formulario_alunos_salvar));
        botaoSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FinalizaFormulario();
            }
        });
    }

    private void FinalizaFormulario() {
        PreencheAluno();
        if(aluno.temIdValido()) { // editando aluno
            dao.edita(aluno);
        }else{ // aluno novo
            dao.salva(aluno);
        }
        finish();
    }

    private void inicializandoCampos() {
        Nome = findViewById(R.id.activity_formulario_alunos_nome);
        Telefone = findViewById(R.id.activity_formulario_alunos_telefone);
        Email = findViewById(R.id.activity_formulario_alunos_email);
    }

    private void PreencheAluno() {
        String nome = Nome.getText().toString();
        String telefone = Telefone.getText().toString();
        String email = Email.getText().toString();

        aluno.setNome(nome);
        aluno.setEmail(email);
        aluno.setTelefone(telefone);
    }
}