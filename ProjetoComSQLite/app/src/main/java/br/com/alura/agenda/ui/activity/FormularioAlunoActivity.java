package br.com.alura.agenda.ui.activity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import br.com.alura.agenda.R;
import br.com.alura.agenda.dao.BancoController;
import br.com.alura.agenda.dao.DAOSQLite;

import static br.com.alura.agenda.ui.activity.ConstantesActivities.CHAVE_ALUNO;

public class FormularioAlunoActivity extends AppCompatActivity {

    private static final String TITULO_APPBAR_NOVO_ALUNO = "Novo aluno";
    private static final String TITULO_APPBAR_EDITA_ALUNO = "Edita aluno";
    private EditText campoNome;
    private EditText campoTelefone;
    private EditText campoEmail;
    private DAOSQLite db;
    private Cursor cursor;
    private BancoController crud;
    private String nome;
    private String telefone;
    private String email;
    private String codigo;
    private Intent dados;
    private boolean edita = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_aluno);

        IniciaCrudeCursor();

        inicializacaoDosCampos();
        carregaAluno();
    }

    private void IniciaCrudeCursor() {
        crud = new BancoController(getBaseContext());
        cursor = crud.carregaDados();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater()
                .inflate(R.menu.activity_formulario_aluno_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if(itemId == R.id.activity_formulario_aluno_menu_salvar){
            if (edita) {
                EditaAluno();
            }else {
                finalizaFormulario();
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void EditaAluno() {
        informacoesParaSalvar();
        crud.alteraRegistro(Integer.parseInt(codigo), nome, telefone, email);
        finish();
    }

    private void informacoesParaSalvar() {
        nome = campoNome.getText().toString();
        telefone = campoTelefone.getText().toString();
        email = campoEmail.getText().toString();
    }

    private void carregaAluno() {
        Intent dados = getIntent();
        if (dados.hasExtra(CHAVE_ALUNO)) {
            edita = true;

            setTitle(TITULO_APPBAR_EDITA_ALUNO);
            String position = dados.getStringExtra(CHAVE_ALUNO);

            cursor.moveToPosition(Integer.parseInt(position));
            codigo = cursor.getString(cursor.getColumnIndexOrThrow(DAOSQLite.ID));
            cursor = crud.carregaDadoById(Integer.parseInt(codigo));

            campoNome.setText(cursor.getString(cursor.getColumnIndexOrThrow(DAOSQLite.NOME)));
            campoTelefone.setText(cursor.getString(cursor.getColumnIndexOrThrow(DAOSQLite.TELEFONE)));
            campoEmail.setText(cursor.getString(cursor.getColumnIndexOrThrow(DAOSQLite.EMAIL)));

        } else {
            setTitle(TITULO_APPBAR_NOVO_ALUNO);
        }
    }

    private void finalizaFormulario() {
        String result;

        informacoesParaSalvar();
        result = crud.salva(nome, telefone, email);
        finish();
    }

    private void inicializacaoDosCampos() {
        campoNome = (EditText)findViewById(R.id.activity_formulario_aluno_nome);
        campoTelefone = (EditText)findViewById(R.id.activity_formulario_aluno_telefone);
        campoEmail = (EditText)findViewById(R.id.activity_formulario_aluno_email);
    }

}
