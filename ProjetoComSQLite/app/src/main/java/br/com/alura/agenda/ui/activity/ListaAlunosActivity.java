package br.com.alura.agenda.ui.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import androidx.annotation.Nullable;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import br.com.alura.agenda.R;
import br.com.alura.agenda.dao.BancoController;
import br.com.alura.agenda.dao.DAOSQLite;

import static br.com.alura.agenda.ui.activity.ConstantesActivities.CHAVE_ALUNO;

public class ListaAlunosActivity extends AppCompatActivity {

    private static final String TITULO_APPBAR = "Lista de alunos";
    private ListView lista;
    BancoController crud;
    Cursor cursor;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);
        setTitle(TITULO_APPBAR);

        configuraLista();

        configuraFabNovoAluno();

    }

    @Override
    protected void onResume() {
        super.onResume();
        configuraLista();

        configuraFabNovoAluno();
    }

    private void configuraLista() {
        inicializaCrudeCursor();

        SetandoLista();

        //ConfiguraAdapter(lista);

        SelecionaItemParaVisualização();

        registerForContextMenu(lista);
    }

    private void SelecionaItemParaVisualização() {
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String codigo;
                cursor.moveToPosition(position);
                codigo = cursor.getString(cursor.getColumnIndexOrThrow(DAOSQLite.ID));
                Intent intent = new Intent(ListaAlunosActivity.this, ViewAlunoActivity.class);
                intent.putExtra("codigo", codigo);
                startActivity(intent);
            }
        });
    }

    private void SetandoLista() {
        String[] nomeCampos = new String[] {DAOSQLite.NOME ,DAOSQLite.TELEFONE, DAOSQLite.ID};
        int[] idViews = new int[] {R.id.item_aluno_nome, R.id.item_aluno_telefone};

        SimpleCursorAdapter adaptador = new SimpleCursorAdapter(getBaseContext(),
                R.layout.item_aluno,cursor,nomeCampos,idViews, 0);
        lista = (ListView)findViewById(R.id.activity_lista_alunos_listview);
        lista.setAdapter(adaptador);
    }

    private void inicializaCrudeCursor() {
        crud = new BancoController(getBaseContext());
        cursor = crud.carregaDados();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.activity_lista_alunos_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        CharSequence title = item.getTitle();
        if ("Remover".equals(title)) {
            ConfirmaRemocao(menuInfo.position);
        } else if ("Editar".equals(title)) {
            AbreFormularioModoEditaAluno(menuInfo.position);
        }
        return super.onContextItemSelected(item);
    }

    private void ConfirmaRemocao(int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Removendo Aluno");
        builder.setMessage("Tem certeza que deseja remover o aluno?");
        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                RemoveItemSelecionado(position);
            }
        });
        builder.setNegativeButton("Não", null);
        builder.show();
    }

    private void RemoveItemSelecionado(int position) {

        //Remoção do item selecionado
        String codigo;
        cursor.moveToPosition(position);
        codigo = cursor.getString(cursor.getColumnIndexOrThrow(DAOSQLite.ID));
        cursor = crud.carregaDadoById(Integer.parseInt(codigo));
        crud.deletaRegistro(Integer.parseInt(codigo));
        Refresh();
    }

    private void Refresh() {
        finish();
        overridePendingTransition(0, 0);
        startActivity(getIntent());
        overridePendingTransition(0, 0);
    }

    private void configuraFabNovoAluno() {
        FloatingActionButton botaoNovoAluno = findViewById(R.id.activity_lista_alunos_fab_novo_aluno);
        botaoNovoAluno.setOnClickListener(view -> abreFormularioModoInsereAluno());
    }

    private void abreFormularioModoInsereAluno() {
        startActivity(new Intent(this, FormularioAlunoActivity.class));
    }

    private void AbreFormularioModoEditaAluno(int position) {
        Intent vaiParaFormularioActivity = new Intent(ListaAlunosActivity.this, FormularioAlunoActivity.class);
        vaiParaFormularioActivity.putExtra(CHAVE_ALUNO, String.valueOf(position));
        startActivity(vaiParaFormularioActivity);
    }

}
