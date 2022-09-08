package com.example.myapplication.dao;

import com.example.myapplication.model.Aluno;

import java.util.ArrayList;
import java.util.List;

public class AlunoDAO {
    private static int contadorIds = 1;
    private final static List<Aluno> alunos = new ArrayList<>();
    public void salva(Aluno aluno) {
                //tentar integrar com mysql
        alunos.add(aluno);
        aluno.setId(contadorIds);
        AtualizaId();
    }

    private void AtualizaId() {
        contadorIds++;
    }

    public void edita(Aluno aluno){
        Aluno alunoEncontrado = BuscaAlunoPorId(aluno);
        if(alunoEncontrado != null){
            int posicaoDoAluno = alunos.indexOf(alunoEncontrado);
            alunos.set(posicaoDoAluno, aluno);
        }

    }

    private Aluno BuscaAlunoPorId(Aluno aluno) {
        for (Aluno a: alunos) {
            if (a.getId() == aluno.getId()) {
                return a;
            }
        }
        return null;
    }

    public List<Aluno> todos() {
        return new ArrayList<>(alunos);
    }

    public void remove(Aluno aluno) {
        Aluno alunoDevolvido = BuscaAlunoPorId(aluno);
        if(alunoDevolvido != null){
            alunos.remove(alunoDevolvido);
        }

    }
}
