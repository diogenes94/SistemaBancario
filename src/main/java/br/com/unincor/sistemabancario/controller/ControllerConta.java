/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.unincor.sistemabancario.controller;

import br.com.unincor.sistemabancario.model.dao.AgenciaDao;
import br.com.unincor.sistemabancario.model.dao.ContaDao;
import br.com.unincor.sistemabancario.model.domain.Agencia;
import br.com.unincor.sistemabancario.model.domain.Conta;
import br.com.unincor.sistemabancario.view.TelaCadastroConta;
import br.com.unincor.sistemabancario.view.tables.TabelaConta;
import java.util.List;
import javax.swing.JComboBox;

/**
 *
 * @author dioge
 */
public class ControllerConta {
    
    public void pesquisarContas(TelaCadastroConta t) {
        var contaDao = new ContaDao();
        List<Conta> contas = contaDao.buscarTodasContas();
        TabelaConta tabelaConta = (TabelaConta) t.getjTable1()
                .getModel();
        tabelaConta.setRegistros(contas);
        
    }
    
    public void carregarAgenciasCombo(JComboBox<Object> comboBox) {
        List<Agencia> agencias = new AgenciaDao().buscarTodasAgencias();
        comboBox.removeAllItems();
        agencias.forEach(comboBox::addItem);
    }
}
