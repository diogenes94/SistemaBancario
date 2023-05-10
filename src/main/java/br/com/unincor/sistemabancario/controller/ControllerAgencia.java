package br.com.unincor.sistemabancario.controller;

import br.com.unincor.sistemabancario.exceptions.AgenciaException;
import br.com.unincor.sistemabancario.model.dao.AgenciaDao;
import br.com.unincor.sistemabancario.model.domain.Agencia;
import br.com.unincor.sistemabancario.utils.GerarMensagens;
import br.com.unincor.sistemabancario.view.TelaNovaAgencia;

/**
 *
 * @author dioge
 */
public class ControllerAgencia {

    public void salvar(TelaNovaAgencia t) throws AgenciaException {

        if (t.getCampoNome().getText().isBlank()) {
            throw new AgenciaException("O nome não foi informado!");
        }
        
        if(t.getCampoEndereco().getText().isBlank()) {
            throw new AgenciaException("O Endereço não foi informado!");
        }
        
        if(t.getCampoTelefone().getText().isBlank()) {
            throw new AgenciaException("O campo telefone não foi"
                    + " informado");
        }
        
        var agencia = new Agencia(null, t.getCampoNome().getText(),
                t.getCampoEndereco().getText(),
                t.getCampoTelefone().getText());
        
        var agenciaDao = new AgenciaDao();
        agenciaDao.inserir(agencia);
        
        GerarMensagens.alerta(t, "Salvo com sucesso!");
        t.dispose();
    }

}
