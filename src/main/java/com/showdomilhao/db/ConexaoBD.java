/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.showdomilhao.db;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author gusta
 */
public class ConexaoBD {
        private static final String URL     = "jdbc:mysql://localhost:3306/show_do_milhao?useSSL=false&serverTimezone=UTC";
    private static final String USUARIO = "root"; 
    private static final String SENHA   = "root";     

    private static Connection conexao = null;

    public static Connection getConexao() {
        try {
            if (conexao == null || conexao.isClosed()) {
                conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
            }
        } catch (SQLException e) {
            javax.swing.JOptionPane.showMessageDialog(null,
                "Erro ao conectar ao banco de dados:\n" + e.getMessage(),
                "Erro de Conexão", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
        return conexao;
    }

    public static void fechar() {
        try {
            if (conexao != null && !conexao.isClosed()) conexao.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
