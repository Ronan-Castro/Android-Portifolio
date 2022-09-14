package br.com.alura.agenda.ui.activity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import br.com.alura.agenda.R;
import br.com.alura.agenda.dao.BancoController;
import br.com.alura.agenda.dao.DAOSQLite;


public class ViewAlunoActivity extends AppCompatActivity {
    private int position;

    private TextView Nome;
    private TextView Telefone;
    private TextView Email;
    Cursor cursor;
    BancoController crud;
    String codigo;

    public static final String TITULO_APPBAR = "Aluno";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_abre_aluno);
        setTitle(TITULO_APPBAR);

        codigo = this.getIntent().getStringExtra("codigo");

        crud = new BancoController(getBaseContext());

        IniciandoViews();

        SetandoTexto();

        BotaoVoltar();
    }

    private void SetandoTexto() {
        cursor = crud.carregaDadoById(Integer.parseInt(codigo));
        Nome.setText(cursor.getString(cursor.getColumnIndexOrThrow(DAOSQLite.NOME)));
        Telefone.setText(cursor.getString(cursor.getColumnIndexOrThrow(DAOSQLite.TELEFONE)));
        Email.setText(cursor.getString(cursor.getColumnIndexOrThrow(DAOSQLite.EMAIL)));
    }

    private void IniciandoViews() {
        Nome = (TextView) findViewById(R.id.activity_aluno_nome);
        Email = (TextView) findViewById(R.id.activity_aluno_email);
        Telefone = (TextView) findViewById(R.id.activity_aluno_telefone);
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
