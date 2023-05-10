/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.unincor.sistemabancario.utils;

import java.awt.Component;
import javax.swing.JOptionPane;

/**
 *
 * @author dioge
 */
public class GerarMensagens {
    
    public static void alerta(Component parente, Object mensagem) {
        JOptionPane.showMessageDialog(parente, mensagem);
    }
    
    public static void erro(Component parente, Object mensagem) {
        JOptionPane.showMessageDialog(parente, mensagem,
                "Erro", JOptionPane.ERROR_MESSAGE);
    }
    
}
