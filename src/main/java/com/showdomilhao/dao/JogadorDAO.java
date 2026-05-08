/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.showdomilhao.dao;

import com.showdomilhao.db.ConexaoBD;
import com.showdomilhao.model.Jogador;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author gusta
 */
public class JogadorDAO {
        public void inserir(Jogador j) throws SQLException {
        String sql = "INSERT INTO jogador (nome, pontuacao) VALUES (?, ?)";
        try (PreparedStatement ps = ConexaoBD.getConexao().prepareStatement(sql)) {
            ps.setString(1, j.getNome());
            ps.setInt(2, j.getPontuacao());
            ps.executeUpdate();
        }
    }
            // Busca por nome. Retorna null se não existir.
    public Jogador buscarPorNome(String nome) throws SQLException {
        String sql = "SELECT * FROM jogador WHERE nome = ?";
        try (PreparedStatement ps = ConexaoBD.getConexao().prepareStatement(sql)) {
            ps.setString(1, nome);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Jogador(rs.getInt("id"), rs.getString("nome"), rs.getInt("pontuacao"));
            }
        }
        return null;
    }

    // Cria se não existir, retorna sempre o jogador com ID
    public Jogador buscarOuCriar(String nome) throws SQLException {
        Jogador j = buscarPorNome(nome);
        if (j != null) return j;
        inserir(new Jogador(nome));
        return buscarPorNome(nome);
    }

    // Ranking ordenado pelo melhor prêmio
    public List<Jogador> listarRanking() throws SQLException {
        String sql = "SELECT * FROM jogador ORDER BY pontuacao DESC";
        List<Jogador> lista = new ArrayList<>();
        try (Statement st = ConexaoBD.getConexao().createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new Jogador(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getInt("pontuacao")
                ));
            }
        }
        return lista;
    }

    // Todos os jogadores ordenados por nome (para JComboBox)
    public List<Jogador> listarTodos() throws SQLException {
        String sql = "SELECT * FROM jogador ORDER BY nome";
        List<Jogador> lista = new ArrayList<>();
        try (Statement st = ConexaoBD.getConexao().createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new Jogador(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getInt("pontuacao")
                ));
            }
        }
        return lista;
    }

    // Atualiza pontuação SOMENTE se o novo prêmio for maior que o recorde
    public void atualizarMelhorPremio(int id, int premio) throws SQLException {
        String sql = "UPDATE jogador SET pontuacao = ? WHERE id = ? AND pontuacao < ?";
        try (PreparedStatement ps = ConexaoBD.getConexao().prepareStatement(sql)) {
            ps.setInt(1, premio);
            ps.setInt(2, id);
            ps.setInt(3, premio);
            ps.executeUpdate();
        }
    }

    // Adiciona pontos extras (chaveamento)
    public void adicionarPontos(int id, int pontos) throws SQLException {
        String sql = "UPDATE jogador SET pontuacao = pontuacao + ? WHERE id = ?";
        try (PreparedStatement ps = ConexaoBD.getConexao().prepareStatement(sql)) {
            ps.setInt(1, pontos);
            ps.setInt(2, id);
            ps.executeUpdate();
        }
    }

    public void deletar(int id) throws SQLException {
        String sql = "DELETE FROM jogador WHERE id = ?";
        try (PreparedStatement ps = ConexaoBD.getConexao().prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    public void resetarRanking() throws SQLException {
        try (Statement st = ConexaoBD.getConexao().createStatement()) {
            st.executeUpdate("UPDATE jogador SET pontuacao = 0");
        }
    }
}
