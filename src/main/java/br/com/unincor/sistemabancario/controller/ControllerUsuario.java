package br.com.unincor.sistemabancario.controller;

import br.com.unincor.sistemabancario.exceptions.UsuarioException;
import br.com.unincor.sistemabancario.model.dao.UsuarioDao;
import br.com.unincor.sistemabancario.utils.Criptografar;
import br.com.unincor.sistemabancario.view.TelaLogin;
import br.com.unincor.sistemabancario.view.TelaPrincipal;

public class ControllerUsuario {
    
    public void efetuarLogin(TelaLogin t) throws UsuarioException {
        var usuario = t.getjTEmail().getText();
        
        /* Criar um String a partir de um array de bytes para a senha */
        var senha = new String(t.getjTSenha().getPassword());
        
        var senhaCriptografada = Criptografar.encryp(senha);
        
        var optionalUsuario = new UsuarioDao()
                .getUsuarioPorEmail(usuario);
        
        if(optionalUsuario.isEmpty()) {
            throw new UsuarioException("Usuário e/ou senha "
                    + "não encontrados!");
        }
        
        var usuarioLogado = optionalUsuario.get();
        if(usuarioLogado.getSenha().equals(senhaCriptografada)) {
            new TelaPrincipal().setVisible(true);
        } else {
            throw new UsuarioException("Usuário e/ou senha "
                    + "não encontrados!");
        }        
    }
}
