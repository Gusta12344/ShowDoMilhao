/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.showdomilhao;

/**
 *
 * @author gusta
 */
public class TelaJogo extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(TelaJogo.class.getName());

    /**
     * Creates new form TelaJogo
     */
    
private ControladorJogo controlador;
private GerenciadorAjudas ajudas;
private int indiceEscolhido = -1;
private javax.swing.JButton[] botoesResposta;

private com.showdomilhao.model.Jogador jogadorAtual;
private com.showdomilhao.dao.JogadorDAO jogadorDAO = new com.showdomilhao.dao.JogadorDAO();
private com.showdomilhao.dao.PartidaDAO partidaDAO = new com.showdomilhao.dao.PartidaDAO();

    public TelaJogo() {
        initComponents();
        
    controlador = new ControladorJogo();
    ajudas = new GerenciadorAjudas();
    botoesResposta = new javax.swing.JButton[]{btnA, btnB, btnC, btnD};

    carregarPergunta();
    this.pack();
    this.setLocationRelativeTo(null); // centraliza na tela
    }
    
    public TelaJogo(com.showdomilhao.model.Jogador jogador) {
    initComponents(); 
    this.jogadorAtual = jogador;
    this.setTitle("Show do Milhão — " + jogador.getNome());


    controlador = new ControladorJogo();
    ajudas = new GerenciadorAjudas();
    botoesResposta = new javax.swing.JButton[]{btnA, btnB, btnC, btnD};
    carregarPergunta();
    this.pack();
    this.setLocationRelativeTo(null);
}
    
    private void carregarPergunta() {
    Pergunta p = controlador.getPerguntaAtual();
    lblPergunta.setText("<html><center>" + p.getEnunciado() + "</center></html>");

    String[] alts = p.getAlternativas();
    String[] letras = {"1. ", "2. ", "3. ", "4. "};
    for (int i = 0; i < 4; i++) {
        botoesResposta[i].setText(letras[i] + alts[i]);
        botoesResposta[i].setEnabled(true);
        botoesResposta[i].setBackground(null); // reseta cor
         botoesResposta[i].setVisible(true);
    }

    pnlAcao.setVisible(false);
    indiceEscolhido = -1;

    // Atualiza barra de prêmios
    lblErrar.setText("<html><center>ERRAR<br>" +
        ControladorJogo.formatarPremio(controlador.getPremioSeErrar()) + "</center></html>");
    lblParar.setText("<html><center>PARAR<br>" +
        ControladorJogo.formatarPremio(controlador.getPremioAtual()) + "</center></html>");
    lblAcertar.setText("<html><center>ACERTAR<br>" +
        ControladorJogo.formatarPremio(controlador.getPremioSeAcertar()) + "</center></html>");
}
    
    private void responder(int indice) {
    indiceEscolhido = indice;

    // Desabilita todos os botões
    for (javax.swing.JButton btn : botoesResposta) btn.setEnabled(false);

    boolean acertou = controlador.responder(indice);

    if (acertou) {
        botoesResposta[indice].setBackground(java.awt.Color.GREEN);
        pnlAcao.setVisible(true); // mostra CONTINUAR e PARAR
    } else {
        botoesResposta[indice].setBackground(java.awt.Color.RED);
        // Mostra também qual era a correta
        botoesResposta[controlador.getPerguntaAtual().getIndiceCorreto()]
            .setBackground(java.awt.Color.GREEN);

        encerrarJogo(controlador.getPremioSeErrar());
    }
}
    
    private void usarPulo(javax.swing.JButton btnUsado) {
    if (!ajudas.podePular()) { mostrarAjudaUsada(); return; }
    ajudas.usarPulo();
    btnUsado.setEnabled(false);
    controlador.avancar(); // pula a pergunta sem pontuar
    carregarPergunta();
    // Restaura visibilidade dos botões caso cartas tenha sido usada
    for (javax.swing.JButton btn : botoesResposta) btn.setVisible(true);
}

private void mostrarAjudaUsada() {
    javax.swing.JOptionPane.showMessageDialog(this, "Ajuda já utilizada!");
}

private void encerrarJogo(int premioFinal) {
    // Salva no banco somente se veio de um jogador logado
    if (jogadorAtual != null) {
        try {
            jogadorDAO.atualizarMelhorPremio(jogadorAtual.getId(), premioFinal);
            partidaDAO.registrarJogo(jogadorAtual.getId(), premioFinal);
        } catch (java.sql.SQLException e) {
            System.err.println("Erro ao salvar resultado: " + e.getMessage());
        }
    }

    String premioFormatado = ControladorJogo.formatarPremio(premioFinal);
    String nomeExibido = jogadorAtual != null ? jogadorAtual.getNome() : "Jogador";
    String mensagem = nomeExibido + ", você ganhou:\n" + premioFormatado + "\n\nO que deseja fazer?";

    String[] opcoes = {"🔄 Jogar Novamente", "🏅 Ver Ranking", "❌ Sair"};
    int escolha = javax.swing.JOptionPane.showOptionDialog(this, mensagem, "Fim de Jogo",
        javax.swing.JOptionPane.DEFAULT_OPTION,
        javax.swing.JOptionPane.INFORMATION_MESSAGE,
        null, opcoes, opcoes[0]);

    if (escolha == 0) {
        // Reinicia o jogo com o mesmo jogador
        controlador = new ControladorJogo();
        ajudas = new GerenciadorAjudas();
        for (javax.swing.JButton btn : botoesResposta) btn.setVisible(true);
        btnCartas.setEnabled(true);     btnPlacas.setEnabled(true);
        btnConvidados.setEnabled(true);
        btnPular1.setEnabled(true);     btnPular2.setEnabled(true);
        btnPular3.setEnabled(true);
        carregarPergunta();

    } else if (escolha == 1) {
        TelaInicio inicio = new TelaInicio();
        new TelaRanking(inicio).setVisible(true);
        this.dispose();

    } else {
        System.exit(0);
    }
}

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlHeader = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        pnlAjudas = new javax.swing.JPanel();
        btnCartas = new javax.swing.JButton();
        btnPlacas = new javax.swing.JButton();
        btnConvidados = new javax.swing.JButton();
        btnPular1 = new javax.swing.JButton();
        btnPular2 = new javax.swing.JButton();
        btnPular3 = new javax.swing.JButton();
        pnlJogo = new javax.swing.JPanel();
        lblPergunta = new javax.swing.JLabel();
        pnlRespostas = new javax.swing.JPanel();
        btnA = new javax.swing.JButton();
        btnB = new javax.swing.JButton();
        btnC = new javax.swing.JButton();
        btnD = new javax.swing.JButton();
        pnlAcao = new javax.swing.JPanel();
        btnContinuar = new javax.swing.JButton();
        btnParar = new javax.swing.JButton();
        pnlPremio = new javax.swing.JPanel();
        lblErrar = new javax.swing.JLabel();
        lblParar = new javax.swing.JLabel();
        lblAcertar = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Show Do Milhão");
        setMinimumSize(new java.awt.Dimension(700, 600));
        setPreferredSize(new java.awt.Dimension(700, 600));
        setResizable(false);
        setSize(new java.awt.Dimension(700, 600));

        pnlHeader.setBackground(new java.awt.Color(26, 35, 126));
        pnlHeader.setMinimumSize(new java.awt.Dimension(700, 100));
        pnlHeader.setLayout(new java.awt.BorderLayout());

        lblTitulo.setFont(new java.awt.Font("Segoe UI", 1, 28)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(255, 215, 0));
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("SHOW DO MILHÃO");
        lblTitulo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        pnlHeader.add(lblTitulo, java.awt.BorderLayout.CENTER);

        pnlAjudas.setBackground(new java.awt.Color(26, 35, 126));

        btnCartas.setText("Cartas");
        btnCartas.addActionListener(this::btnCartasActionPerformed);
        pnlAjudas.add(btnCartas);

        btnPlacas.setText("Placas");
        btnPlacas.addActionListener(this::btnPlacasActionPerformed);
        pnlAjudas.add(btnPlacas);

        btnConvidados.setText("Convidados");
        btnConvidados.addActionListener(this::btnConvidadosActionPerformed);
        pnlAjudas.add(btnConvidados);

        btnPular1.setText("Pular 1");
        btnPular1.addActionListener(this::btnPular1ActionPerformed);
        pnlAjudas.add(btnPular1);

        btnPular2.setText("Pular 2");
        btnPular2.addActionListener(this::btnPular2ActionPerformed);
        pnlAjudas.add(btnPular2);

        btnPular3.setText("Pular 3");
        btnPular3.addActionListener(this::btnPular3ActionPerformed);
        pnlAjudas.add(btnPular3);

        pnlHeader.add(pnlAjudas, java.awt.BorderLayout.SOUTH);

        getContentPane().add(pnlHeader, java.awt.BorderLayout.NORTH);

        pnlJogo.setBackground(new java.awt.Color(13, 71, 161));
        pnlJogo.setLayout(new java.awt.BorderLayout());

        lblPergunta.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblPergunta.setForeground(new java.awt.Color(255, 255, 255));
        lblPergunta.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPergunta.setText("Pergunta");
        lblPergunta.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblPergunta.setMaximumSize(new java.awt.Dimension(0, 90));
        lblPergunta.setMinimumSize(new java.awt.Dimension(0, 90));
        lblPergunta.setPreferredSize(new java.awt.Dimension(0, 90));
        pnlJogo.add(lblPergunta, java.awt.BorderLayout.NORTH);

        pnlRespostas.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        pnlRespostas.setLayout(new java.awt.GridLayout(2, 2, 10, 10));

        btnA.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnA.setText("1. Alternativa A");
        btnA.setOpaque(true);
        btnA.addActionListener(this::btnAActionPerformed);
        pnlRespostas.add(btnA);

        btnB.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnB.setText("2. Alternativa B");
        btnB.setOpaque(true);
        btnB.addActionListener(this::btnBActionPerformed);
        pnlRespostas.add(btnB);

        btnC.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnC.setText("3. Alternativa C");
        btnC.setOpaque(true);
        btnC.addActionListener(this::btnCActionPerformed);
        pnlRespostas.add(btnC);

        btnD.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnD.setText("4. Alternativa D");
        btnD.setOpaque(true);
        btnD.addActionListener(this::btnDActionPerformed);
        pnlRespostas.add(btnD);

        pnlJogo.add(pnlRespostas, java.awt.BorderLayout.CENTER);

        pnlAcao.setMaximumSize(new java.awt.Dimension(0, 60));
        pnlAcao.setMinimumSize(new java.awt.Dimension(0, 60));
        pnlAcao.setPreferredSize(new java.awt.Dimension(0, 60));

        btnContinuar.setBackground(new java.awt.Color(76, 175, 80));
        btnContinuar.setForeground(new java.awt.Color(255, 255, 255));
        btnContinuar.setText("Continuar");
        btnContinuar.addActionListener(this::btnContinuarActionPerformed);
        pnlAcao.add(btnContinuar);

        btnParar.setBackground(new java.awt.Color(244, 67, 54));
        btnParar.setForeground(new java.awt.Color(255, 255, 255));
        btnParar.setText("Parar");
        btnParar.addActionListener(this::btnPararActionPerformed);
        pnlAcao.add(btnParar);

        pnlJogo.add(pnlAcao, java.awt.BorderLayout.SOUTH);

        getContentPane().add(pnlJogo, java.awt.BorderLayout.CENTER);

        pnlPremio.setBackground(new java.awt.Color(33, 33, 33));
        pnlPremio.setMaximumSize(new java.awt.Dimension(0, 80));
        pnlPremio.setMinimumSize(new java.awt.Dimension(0, 80));
        pnlPremio.setPreferredSize(new java.awt.Dimension(0, 80));
        pnlPremio.setLayout(new java.awt.GridLayout(1, 3));

        lblErrar.setForeground(new java.awt.Color(239, 83, 80));
        lblErrar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblErrar.setText("ERRAR\\nR$ 0");
        pnlPremio.add(lblErrar);

        lblParar.setForeground(new java.awt.Color(255, 215, 0));
        lblParar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblParar.setText("PARAR\\nR$ 0");
        pnlPremio.add(lblParar);

        lblAcertar.setForeground(new java.awt.Color(102, 187, 106));
        lblAcertar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAcertar.setText("ACERTAR\\nR$ 1.000");
        pnlPremio.add(lblAcertar);

        getContentPane().add(pnlPremio, java.awt.BorderLayout.SOUTH);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAActionPerformed
        // TODO add your handling code here:
        responder(0);
    }//GEN-LAST:event_btnAActionPerformed

    private void btnBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBActionPerformed
        // TODO add your handling code here:
        responder(1);
    }//GEN-LAST:event_btnBActionPerformed

    private void btnCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCActionPerformed
        // TODO add your handling code here:
        responder(2);
    }//GEN-LAST:event_btnCActionPerformed

    private void btnDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDActionPerformed
        // TODO add your handling code here:
        responder(3);
    }//GEN-LAST:event_btnDActionPerformed

    private void btnContinuarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnContinuarActionPerformed
        // TODO add your handling code here:
        if (controlador.isUltimaPergunta()) {
       encerrarJogo(1_000_000);
    } else {
        controlador.avancar();
        carregarPergunta();
    }
    }//GEN-LAST:event_btnContinuarActionPerformed

    private void btnPararActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPararActionPerformed
        // TODO add your handling code here:
         javax.swing.JOptionPane.showMessageDialog(this,
        "Você decidiu parar! Ganha " +
        ControladorJogo.formatarPremio(controlador.getPremioAtual()),
        "Jogo encerrado", javax.swing.JOptionPane.INFORMATION_MESSAGE);
    encerrarJogo(controlador.getPremioAtual());
    }//GEN-LAST:event_btnPararActionPerformed

    private void btnCartasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCartasActionPerformed
        // TODO add your handling code here:
         int[] esconder = ajudas.usarCartas(controlador.getPerguntaAtual());
    if (esconder == null) { mostrarAjudaUsada(); return; }
    for (int i : esconder) botoesResposta[i].setVisible(false);
    btnCartas.setEnabled(false);
    }//GEN-LAST:event_btnCartasActionPerformed

    private void btnConvidadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConvidadosActionPerformed
        // TODO add your handling code here:
            String dica = ajudas.usarConvidados(controlador.getPerguntaAtual());
    if (dica == null) { mostrarAjudaUsada(); return; }
    javax.swing.JOptionPane.showMessageDialog(this, dica, "Convidado", 0);
    btnConvidados.setEnabled(false);
    }//GEN-LAST:event_btnConvidadosActionPerformed

    private void btnPlacasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPlacasActionPerformed
        // TODO add your handling code here:
            int[] votos = ajudas.usarPlacas(controlador.getPerguntaAtual());
    if (votos == null) { mostrarAjudaUsada(); return; }
    StringBuilder sb = new StringBuilder("Votação da plateia:\n");
    String[] letras = {"A", "B", "C", "D"};
    for (int i = 0; i < 4; i++) sb.append(letras[i]).append(": ").append(votos[i]).append("%\n");
    javax.swing.JOptionPane.showMessageDialog(this, sb.toString(), "Placas", 0);
    btnPlacas.setEnabled(false);
    }//GEN-LAST:event_btnPlacasActionPerformed

    private void btnPular1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPular1ActionPerformed
        // TODO add your handling code here:
        usarPulo(btnPular1);
    }//GEN-LAST:event_btnPular1ActionPerformed

    private void btnPular2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPular2ActionPerformed
        // TODO add your handling code here:
        usarPulo(btnPular2);
    }//GEN-LAST:event_btnPular2ActionPerformed

    private void btnPular3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPular3ActionPerformed
        // TODO add your handling code here:
        usarPulo(btnPular3);
    }//GEN-LAST:event_btnPular3ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new TelaJogo().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnA;
    private javax.swing.JButton btnB;
    private javax.swing.JButton btnC;
    private javax.swing.JButton btnCartas;
    private javax.swing.JButton btnContinuar;
    private javax.swing.JButton btnConvidados;
    private javax.swing.JButton btnD;
    private javax.swing.JButton btnParar;
    private javax.swing.JButton btnPlacas;
    private javax.swing.JButton btnPular1;
    private javax.swing.JButton btnPular2;
    private javax.swing.JButton btnPular3;
    private javax.swing.JLabel lblAcertar;
    private javax.swing.JLabel lblErrar;
    private javax.swing.JLabel lblParar;
    private javax.swing.JLabel lblPergunta;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel pnlAcao;
    private javax.swing.JPanel pnlAjudas;
    private javax.swing.JPanel pnlHeader;
    private javax.swing.JPanel pnlJogo;
    private javax.swing.JPanel pnlPremio;
    private javax.swing.JPanel pnlRespostas;
    // End of variables declaration//GEN-END:variables
}
