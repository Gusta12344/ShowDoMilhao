/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.showdomilhao;

/**
 *
 * @author gusta
 */
public class GerenciadorAjudas {
    private boolean cartasUsada = false;
    private boolean placasUsada = false;
    private boolean convidadosUsada = false;
    private int pulosRestantes = 3;

    // Retorna os índices das 2 alternativas erradas para esconder
    public int[] usarCartas(Pergunta p) {
        if (cartasUsada) return null;
        cartasUsada = true;
        int[] errados = new int[2];
        int count = 0;
        for (int i = 0; i < 4 && count < 2; i++) {
            if (i != p.getIndiceCorreto()) {
                errados[count++] = i;
            }
        }
        return errados;
    }

    // Retorna array com % de votos simulados para cada alternativa
    public int[] usarPlacas(Pergunta p) {
        if (placasUsada) return null;
        placasUsada = true;
        int[] votos = new int[4];
        int correto = p.getIndiceCorreto();
        votos[correto] = 55 + (int)(Math.random() * 25); // 55% a 80%
        int restante = 100 - votos[correto];
        int sobra = restante;
        for (int i = 0; i < 4; i++) {
            if (i != correto) {
                if (sobra > 0) {
                    int v = (i == 3) ? sobra : (int)(Math.random() * sobra);
                    votos[i] = v;
                    sobra -= v;
                }
            }
        }
        return votos;
    }

    // Retorna texto de dica do convidado
    public String usarConvidados(Pergunta p) {
        if (convidadosUsada) return null;
        convidadosUsada = true;
        char letra = (char)('A' + p.getIndiceCorreto());
        return "O convidado acredita que a resposta é a alternativa " + letra + "!";
    }

    public boolean podePular() {
        return pulosRestantes > 0;
    }

    public void usarPulo() {
        if (pulosRestantes > 0) pulosRestantes--;
    }

    public boolean isCartasUsada()      { return cartasUsada; }
    public boolean isPlacasUsada()      { return placasUsada; }
    public boolean isConvidadosUsada()  { return convidadosUsada; }
    public int getPulosRestantes()      { return pulosRestantes; }
}
