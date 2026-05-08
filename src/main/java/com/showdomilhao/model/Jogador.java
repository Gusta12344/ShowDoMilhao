/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.showdomilhao.model;

/**
 *
 * @author gusta
 */
public class Jogador {
        private int id;
    private String nome;
    private int pontuacao; 

    public Jogador(int id, String nome, int pontuacao) {
        this.id = id;
        this.nome = nome;
        this.pontuacao = pontuacao;
    }

    public Jogador(String nome) {
        this.nome = nome;
        this.pontuacao = 0;
    }

    public String getMedalha(int posicao) {
        return switch (posicao) {
            case 1 -> "🥇";
            case 2 -> "🥈";
            case 3 -> "🥉";
            default -> "";
        };
    }

    public int getId()                       { return id; }
    public String getNome()                  { return nome; }
    public int getPontuacao()                { return pontuacao; }
    public void setPontuacao(int pontuacao)  { this.pontuacao = pontuacao; }

    @Override
    public String toString() { return nome; }
}
