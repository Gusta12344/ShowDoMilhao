/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.showdomilhao;

/**
 *
 * @author gusta
 */
public class Pergunta {
    private String enunciado;
    private String[] alternativas; // [0]=A, [1]=B, [2]=C, [3]=D
    private int indiceCorreto;

    public Pergunta(String enunciado, String[] alternativas, int indiceCorreto) {
        this.enunciado = enunciado;
        this.alternativas = alternativas;
        this.indiceCorreto = indiceCorreto;
    }

    public boolean isCorreta(int indiceEscolhido) {
        return indiceEscolhido == indiceCorreto;
    }

    public String getEnunciado() { return enunciado; }
    public String[] getAlternativas() { return alternativas; }
    public int getIndiceCorreto() { return indiceCorreto; }
}
