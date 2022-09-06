package com.example.myapplication.dao;

import com.example.myapplication.model.Aluno;

import java.util.ArrayList;
import java.util.List;

public class AlunoDAO {

    private final static List<Aluno> alunos = new ArrayList<>();
    public void salva(Aluno aluno) {
                //tentar integrar com mysql
        alunos.add(aluno);
    }

    public List<Aluno> todos() {
        return new ArrayList<>(alunos);
    }
}
