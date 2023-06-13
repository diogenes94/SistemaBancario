package br.com.unincor.sistemabancario.controller;

import br.com.unincor.sistemabancario.exceptions.ContaException;
import br.com.unincor.sistemabancario.model.dao.ContaDao;
import br.com.unincor.sistemabancario.model.domain.Agencia;
import br.com.unincor.sistemabancario.model.domain.Cliente;
import br.com.unincor.sistemabancario.model.domain.Conta;
import br.com.unincor.sistemabancario.utils.GerarMensagens;
import br.com.unincor.sistemabancario.view.TelaCadastroConta;
import br.com.unincor.sistemabancario.view.TelaNovaConta;
import br.com.unincor.sistemabancario.view.tables.TabelaConta;
import java.util.List;

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
    
    public void salvar(TelaNovaConta t) throws ContaException {
        /* Testa o índice zero do combo para saber se é esta opção selecionada*/
        if(t.getComboCliente().getSelectedIndex() == 0) {
            throw new ContaException("Nenhum cliente foi selecionado!");
        }
        /* Testa o índice zero do combo para saber se é esta opção selecionada*/
        if(t.getComboAgencia().getSelectedIndex() == 0) {
            throw new ContaException("Nenhuma agência foi selecionada!");
        }
        
        /* Cria o objeto conta com os valores informados*/
        Conta conta = new Conta();
        conta.setSaldo(0.0);
        conta.setAgencia((Agencia) t.getComboAgencia().getSelectedItem());
        conta.setCliente((Cliente) t.getComboCliente().getSelectedItem());
        
        /* Cria o objeto DAO (data access object) para inserir no banco */
        var contaDao = new ContaDao();
        contaDao.inserir(conta);
        
        /* Gera uma mensagem de sucesso */
        GerarMensagens.alerta(t, "Salvo com sucesso!");
        /* Pesquisa as contas novamente para atualizar a tela*/
        pesquisarContas((TelaCadastroConta) t.getParent());
        /* Fecha a janela de cadastro */
        t.dispose();
    }
    
}
