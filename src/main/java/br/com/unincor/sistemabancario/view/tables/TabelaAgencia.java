/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.unincor.sistemabancario.view.tables;

import br.com.unincor.sistemabancario.model.domain.Agencia;

/**
 *
 * @author dioge
 */
public class TabelaAgencia extends AbstractTable<Agencia>{

    public TabelaAgencia() {
        this("id", "Nome", "Endereço",
                "Telefone");
    }
    
    public TabelaAgencia(String... colunas) {
        super(colunas);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Agencia agencia = getRegistros().get(rowIndex);
        return switch (columnIndex) {
            case 0 -> agencia.getId();
            case 1 -> agencia.getNome();
            case 2 -> agencia.getEndereco();
            case 3 -> agencia.getTelefone();
            default -> null;
        };
    }
    
}
