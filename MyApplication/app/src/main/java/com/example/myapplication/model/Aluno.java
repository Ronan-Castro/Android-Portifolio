package com.example.myapplication.model;

import android.support.annotation.NonNull;

import java.io.Serializable;

public class Aluno implements Serializable {
    public String nome;
    private int id = 0;
    private String telefone;
    private String email;

    public Aluno(String nome, String telefone, String email) {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
    }

    public Aluno() {

    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @NonNull
    @Override
    public String toString() {
    return nome + " - " + telefone;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public boolean temIdValido() {
        return id > 0;
    }

    public String getNome() {
        return nome;
    }
}
