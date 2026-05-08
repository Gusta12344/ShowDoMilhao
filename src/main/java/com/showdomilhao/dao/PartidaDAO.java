/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.showdomilhao.dao;

import com.showdomilhao.db.ConexaoBD;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gusta
 */
public class PartidaDAO {
        // Registra resultado de uma partida de chaveamento
    public void registrarPartida(int j1Id, int j2Id, int vencedorId) throws SQLException {
        String sql = "INSERT INTO partida (jogador1_id, jogador2_id, vencedor_id) VALUES (?,?,?)";
        try (PreparedStatement ps = ConexaoBD.getConexao().prepareStatement(sql)) {
            ps.setInt(1, j1Id);
            ps.setInt(2, j2Id);
            ps.setInt(3, vencedorId);
            ps.executeUpdate();
        }
    }

    // Registra uma partida do Show do Milhão
    public void registrarJogo(int jogadorId, int premio) throws SQLException {
        String sql = "INSERT INTO historico_jogo (jogador_id, premio) VALUES (?, ?)";
        try (PreparedStatement ps = ConexaoBD.getConexao().prepareStatement(sql)) {
            ps.setInt(1, jogadorId);
            ps.setInt(2, premio);
            ps.executeUpdate();
        }
    }

    // Retorna histórico formatado para a JTable: [Nome, Prêmio, Data]
    public List<Object[]> listarHistoricoJogos() throws SQLException {
        String sql = """
            SELECT j.nome, h.premio, h.data_jogo
            FROM historico_jogo h
            JOIN jogador j ON h.jogador_id = j.id
            ORDER BY h.data_jogo DESC
        """;
        List<Object[]> lista = new ArrayList<>();
        try (Statement st = ConexaoBD.getConexao().createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new Object[]{
                    rs.getString("nome"),
                    String.format("R$ %,d", rs.getInt("premio")).replace(',', '.'),
                    rs.getTimestamp("data_jogo").toLocalDateTime()
                        .format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))
                });
            }
        }
        return lista;
    }

    // Histórico do chaveamento: [J1, J2, Vencedor, Data]
    public List<Object[]> listarHistoricoChaveamento() throws SQLException {
        String sql = """
            SELECT j1.nome AS nm1, j2.nome AS nm2, jv.nome AS nmv, p.data_partida
            FROM partida p
            JOIN jogador j1 ON p.jogador1_id = j1.id
            JOIN jogador j2 ON p.jogador2_id = j2.id
            LEFT JOIN jogador jv ON p.vencedor_id = jv.id
            ORDER BY p.data_partida DESC
        """;
        List<Object[]> lista = new ArrayList<>();
        try (Statement st = ConexaoBD.getConexao().createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new Object[]{
                    rs.getString("nm1"),
                    rs.getString("nm2"),
                    rs.getString("nmv") != null ? rs.getString("nmv") : "—",
                    rs.getTimestamp("data_partida").toLocalDateTime()
                        .format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))
                });
            }
        }
        return lista;
    }
    
    
    public void limparHistorico() throws SQLException {
    try (Statement st = ConexaoBD.getConexao().createStatement()) {
        st.executeUpdate("DELETE FROM historico_jogo");
        st.executeUpdate("DELETE FROM partida");
    }
}
}
