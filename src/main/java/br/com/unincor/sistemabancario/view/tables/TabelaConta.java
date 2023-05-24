package br.com.unincor.sistemabancario.view.tables;

import br.com.unincor.sistemabancario.model.domain.Conta;

/**
 *
 * @author dioge
 */
public class TabelaConta extends AbstractTable<Conta>{

    public TabelaConta() {
        super("id", "Cliente", "Agência", 
                "Saldo");
    }    

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Conta conta = getRegistros().get(rowIndex);
        return switch(columnIndex) {
            case 0 -> conta.getId();
            case 1 -> conta.getCliente().getNome();
            case 2 -> conta.getAgencia().getNome();
            case 3 -> conta.getSaldo();
            default -> null;
        };
    }
    
}
