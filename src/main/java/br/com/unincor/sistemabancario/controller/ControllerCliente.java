/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.unincor.sistemabancario.controller;

import br.com.unincor.sistemabancario.model.dao.ClienteDao;
import br.com.unincor.sistemabancario.model.domain.Cliente;
import java.util.List;
import javax.swing.JComboBox;

/**
 *
 * @author dioge
 */
public class ControllerCliente {
    
    public void carregarClientesCombo(JComboBox<Object> combo) {
        List<Cliente> clientes = new ClienteDao()
                .buscarTodosClientes();
        combo.removeAllItems();
        combo.addItem("(Selecione)");
        clientes.forEach(combo::addItem);
    }
}
