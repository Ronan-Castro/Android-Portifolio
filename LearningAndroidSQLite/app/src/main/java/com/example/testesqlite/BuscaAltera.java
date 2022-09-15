package com.example.testesqlite;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toolbar;


public class BuscaAltera extends AppCompatActivity {
    private ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_first);
        System.out.println((findViewById(R.id.toolbarinicial)));
        setTitle("TITULO_APPBAR");

        ConfiguraLista();

        AdicionaLivro();

    }

    public void onResume(){
        super.onResume();

        ConfiguraLista();

        AdicionaLivro();
    }

    private void ConfiguraLista() {
        BancoController crud = new BancoController(getBaseContext());
        Cursor cursor = crud.carregaDados();

        String[] nomeCampos = new String[] {CriaBanco.ID, CriaBanco.TITULO};
        int[] idViews = new int[] {R.id.idLivro, R.id.nomeLivro};

        SimpleCursorAdapter adaptador = new SimpleCursorAdapter(getBaseContext(),
                R.layout.layout_principal,cursor,nomeCampos,idViews, 0);
        lista = findViewById(R.id.listView_first);
        System.out.println(findViewById(R.id.listView_first));
        lista.setAdapter(adaptador);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String codigo;
                cursor.moveToPosition(position);
                codigo = cursor.getString(cursor.getColumnIndexOrThrow(CriaBanco.ID));
                Intent intent = new Intent(BuscaAltera.this, AlteraRemove.class);
                intent.putExtra("codigo", codigo);
                startActivity(intent);
                finish();
            }
        });
    }

    private void AdicionaLivro() {
        FloatingActionButton botaoAdd = findViewById((R.id.add_livro));
        botaoAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BuscaAltera.this, InsereDado.class);
                startActivity(intent);
            }
        });
    }
}
