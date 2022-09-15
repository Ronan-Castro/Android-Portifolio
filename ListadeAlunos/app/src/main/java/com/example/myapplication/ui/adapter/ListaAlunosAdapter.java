package com.example.myapplication.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.model.Aluno;
import com.example.myapplication.ui.activity.ListaAlunosActivity;

import java.util.ArrayList;
import java.util.List;

public class ListaAlunosAdapter extends BaseAdapter {
    private final List<Aluno> alunos = new ArrayList<>();
    private Context context;

    public ListaAlunosAdapter(Context context){
        this.context = context;
    }

    @Override
    public int getCount() {
        return alunos.size();
    }

    @Override
    public Aluno getItem(int position) {
        return alunos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return alunos.get(position).getId();
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        View viewCriada = CriaView(viewGroup);

        Aluno alunoDevolvido = alunos.get(position);

        PreencheTextView(viewCriada, alunoDevolvido);

        return viewCriada;
    }

    private View CriaView(ViewGroup viewGroup) {
        View viewCriada = LayoutInflater.from(context).inflate(R.layout.item_aluno, viewGroup,false);
        return viewCriada;
    }

    private void PreencheTextView(View viewCriada, Aluno alunoDevolvido) {
        TextView Nome = viewCriada.findViewById(R.id.item_aluno_nome);
        TextView Telefone = viewCriada.findViewById(R.id.item_aluno_telefone);
        Nome.setText(alunoDevolvido.getNome());
        Telefone.setText((alunoDevolvido.getTelefone()));
    }

    public void atualiza(List<Aluno> alunos) {
        this.alunos.clear();
        this.alunos.addAll(alunos);
        notifyDataSetChanged();
    }

    public void remove(Aluno alunoEscolhido) {
        alunos.remove(alunoEscolhido);
        notifyDataSetChanged();
    }
}
