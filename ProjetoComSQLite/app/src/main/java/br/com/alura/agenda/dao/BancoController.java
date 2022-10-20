package br.com.alura.agenda.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import androidx.annotation.Nullable;


public class BancoController {

    private SQLiteDatabase db;
    private DAOSQLite banco;

    public BancoController(Context context) {
        banco = new DAOSQLite(context);
    }

    public String salva(String nome, String telefone, String email) {

        System.out.println(nome + "inside");
        ContentValues valores;
        long resultado;


        db = banco.getWritableDatabase();
        valores = new ContentValues();
        valores.put(DAOSQLite.NOME, nome);
        valores.put(DAOSQLite.TELEFONE, telefone);
        valores.put(DAOSQLite.EMAIL, email);

        resultado = db.insert(DAOSQLite.TABELA, null, valores);
        db.close();

        if (resultado == -1)
            return "Erro ao inserir registro";
        else
            return "Registro Inserido com sucesso";

    }

    public Cursor carregaDados() {
        Cursor cursor;
        String[] campos = {banco.NOME, banco.TELEFONE, banco.ID};
        db = banco.getReadableDatabase();
        cursor = db.query(banco.TABELA, campos, null, null, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }
        db.close();
        System.out.println(cursor.toString());
        return cursor;
    }
    public Cursor carregaDadoById(int id){
        Cursor cursor;
        String[] campos =  {banco.ID,banco.NOME,banco.TELEFONE,banco.EMAIL};
        String where = DAOSQLite.ID + "=" + id;
        db = banco.getReadableDatabase();
        cursor = db.query(DAOSQLite.TABELA,campos,where, null, null, null, null, null);

        if(cursor!=null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public void alteraRegistro(int id, String nome, String telefone, String email){
        ContentValues valores;
        String where;

        db = banco.getWritableDatabase();

        where = DAOSQLite.ID + "=" + id;

        valores = new ContentValues();
        valores.put(DAOSQLite.NOME, nome);
        valores.put(DAOSQLite.TELEFONE, telefone);
        valores.put(DAOSQLite.EMAIL, email);

        db.update(DAOSQLite.TABELA,valores,where,null);
        db.close();
    }

    public void deletaRegistro(int id){
        String where = DAOSQLite.ID + "=" + id;
        db = banco.getReadableDatabase();
        db.delete(DAOSQLite.TABELA,where,null);
        db.close();
    }

}

