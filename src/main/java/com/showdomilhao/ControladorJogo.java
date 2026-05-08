/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.showdomilhao;

/**
 *
 * @author gusta
 */
public class ControladorJogo {
        public static final int TOTAL_PERGUNTAS = 10;

    // Tabela de prêmios — índice = número da pergunta (0 a 9)
    private static final int[] PREMIOS = {
        1_000, 5_000, 10_000, 20_000, 40_000,
        80_000, 150_000, 300_000, 500_000, 1_000_000
    };

    // Níveis seguros: ao errar após esses níveis, garante o prêmio deles
    private static final int[] NIVEIS_SEGUROS = {4, 9}; // índices

    private BancoDePergunta banco;
    private int nivelAtual = 0;

    public ControladorJogo() {
        banco = new BancoDePergunta();
    }

    public Pergunta getPerguntaAtual() {
        return banco.getPergunta(nivelAtual);
    }

    public boolean responder(int indiceEscolhido) {
        return getPerguntaAtual().isCorreta(indiceEscolhido);
    }

    public void avancar() {
        nivelAtual++;
    }

    public boolean isUltimaPergunta() {
        return nivelAtual >= TOTAL_PERGUNTAS - 1;
    }

    // O que o jogador leva se PARAR agora (prêmio do nível atual)
    public int getPremioAtual() {
        if (nivelAtual == 0) return 0;
        return PREMIOS[nivelAtual - 1];
    }

    // O que ganha se ACERTAR esta pergunta
    public int getPremioSeAcertar() {
        return PREMIOS[nivelAtual];
    }

    // O que leva se ERRAR (retrocede ao nível seguro anterior)
    public int getPremioSeErrar() {
        for (int i = NIVEIS_SEGUROS.length - 1; i >= 0; i--) {
            if (nivelAtual > NIVEIS_SEGUROS[i]) return PREMIOS[NIVEIS_SEGUROS[i]];
        }
        return 0; // perdeu tudo
    }

    public int getNivelAtual() { return nivelAtual; }

    public static String formatarPremio(int valor) {
        return String.format("R$ %,d", valor).replace(',', '.');
    }
}
