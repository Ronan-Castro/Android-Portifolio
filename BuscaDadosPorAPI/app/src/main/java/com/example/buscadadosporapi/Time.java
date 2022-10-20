package com.example.buscadadosporapi;

public class Time {
    private String time;
    private int posicao;
    private String mediaValorMercado;
    private String valorTotalMercado;
    private int Ano;

    public String toString(){
        return "Time: " + time + "\nAno: "+ Ano + "\nPosicao: "+ posicao;
    }

    public int getAno() {
        return Ano;
    }

    public void setAno(int ano) {
        Ano = ano;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getPosicao() {
        return posicao;
    }

    public void setPosicao(int posicao) {
        this.posicao = posicao;
    }

    public String getMediaValorMercado() {
        return mediaValorMercado;
    }

    public void setMediaValorMercado(String mediaValorMercado) {
        this.mediaValorMercado = mediaValorMercado;
    }

    public String getValorTotalMercado() {
        return valorTotalMercado;
    }

    public void setValorTotalMercado(String valorTotalMercado) {
        this.valorTotalMercado = valorTotalMercado;
    }

}
