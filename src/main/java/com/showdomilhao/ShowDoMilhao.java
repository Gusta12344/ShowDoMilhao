/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.showdomilhao;

import javax.swing.SwingUtilities;

/**
 *
 * @author gusta
 */
public class ShowDoMilhao {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new TelaJogo().setVisible(true);
        });
    }
}
