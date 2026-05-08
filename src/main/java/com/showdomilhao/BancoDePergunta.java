/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.showdomilhao;

import java.io.*;
import java.util.*;

/**
 *
 * @author gusta
 */
public class BancoDePergunta {
     private List<Pergunta> perguntas = new ArrayList<>();

    public BancoDePergunta() {
        carregarDoArquivo();
        Collections.shuffle(perguntas);
    }
    private void carregarDoArquivo() {
        try (InputStream is = getClass().getResourceAsStream("/perguntas.txt");
             BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"))) {

            String linha;
            while ((linha = br.readLine()) != null) {
                if (linha.isBlank() || linha.startsWith("#")) continue;

                String[] p = linha.split("\\|");
                if (p.length < 6) continue; // linha inválida

                String enunciado = p[0].trim();
                String[] alternativas = {p[1].trim(), p[2].trim(), p[3].trim(), p[4].trim()};
                int indiceCorreto = p[5].trim().toUpperCase().charAt(0) - 'A';

                perguntas.add(new Pergunta(enunciado, alternativas, indiceCorreto));
            }

        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(null,
                "Erro ao carregar perguntas.txt:\n" + e.getMessage());
        }
    }

    public Pergunta getPergunta(int indice) {
        return perguntas.get(indice);
    }

    public int getTotalPerguntas() {
        return Math.min(perguntas.size(), ControladorJogo.TOTAL_PERGUNTAS);
    }
}
