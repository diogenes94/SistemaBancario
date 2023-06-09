package br.com.unincor.sistemabancario.controller;

import br.com.unincor.sistemabancario.exceptions.AgenciaException;
import br.com.unincor.sistemabancario.model.dao.hibernate.AgenciaDao;
import br.com.unincor.sistemabancario.model.domain.Agencia;
import br.com.unincor.sistemabancario.utils.GerarMensagens;
import br.com.unincor.sistemabancario.view.TelaCadastroAgencia;
import br.com.unincor.sistemabancario.view.TelaNovaAgencia;
import br.com.unincor.sistemabancario.view.tables.TabelaAgencia;
import java.util.List;
import javax.swing.JComboBox;

/**
 *
 * @author dioge
 */
public class ControllerAgencia {

    public void salvar(TelaNovaAgencia t) throws AgenciaException {

        if (t.getCampoNome().getText().isBlank()) {
            throw new AgenciaException("O nome não foi informado!");
        }

        if (t.getCampoEndereco().getText().isBlank()) {
            throw new AgenciaException("O Endereço não foi informado!");
        }

        if (t.getCampoTelefone().getText().isBlank()) {
            throw new AgenciaException("O campo telefone não foi"
                    + " informado");
        }

        Integer id = t.getAgenciaEdit() == null
                ? null : t.getAgenciaEdit().getId();
        var agencia = new Agencia(id, t.getCampoNome().getText(),
                t.getCampoEndereco().getText(),
                t.getCampoTelefone().getText());

        var agenciaDao = new AgenciaDao();

        agenciaDao.merge(agencia);

        GerarMensagens.alerta(t, "Salvo com sucesso!");
        pesquisar((TelaCadastroAgencia) t.getParent());
        t.dispose();
    }

    public void pesquisar(TelaCadastroAgencia t) {

        TabelaAgencia tabelaAgencia
                = (TabelaAgencia) t.getjTable1().getModel();
        var agenciaDao = new AgenciaDao();
        tabelaAgencia.setRegistros(
                agenciaDao.buscarTudo());
    }

    public void abrirTelaEdicao(TelaCadastroAgencia t)
            throws AgenciaException {

        if (t.getjTable1().getSelectedRowCount() == 0) {
            throw new AgenciaException(
                    "Nenhum registro foi selecionado!");
        }

        /* Transforma a tabela abstrata no seu formato concreto */
        TabelaAgencia tabelaAgencia = (TabelaAgencia) t.getjTable1().getModel();

        /* retorna a linha selecionada na tabela */
        int row = t.getjTable1().getSelectedRow();

        /* Recupera o registro na lista de objetos da tabela */
        var agencia = tabelaAgencia.getRegistros().get(row);

        new TelaNovaAgencia(t, true, agencia)
                .setVisible(true);
    }

    public void carregarAgenciasCombo(JComboBox<Object> comboBox) {
        List<Agencia> agencias = new AgenciaDao().buscarTudo();
        comboBox.removeAllItems();
        comboBox.addItem("(Selecione)");
        agencias.forEach(comboBox::addItem);
    }

    public void excluirAgencia(TelaCadastroAgencia t) throws AgenciaException {
        if (t.getjTable1().getSelectedRowCount() == 0) {
            throw new AgenciaException("Nenhum registro foi selecionado!");
        }
        /* Pega a linha selecionada na tabela */
        int row = t.getjTable1().getSelectedRow();
        /* recupera o objeto da tabela a partir do jtable */
        TabelaAgencia tabelaAgencia = (TabelaAgencia) t.getjTable1()
                .getModel();
        /* Obtém o objeto selecionado da lista */
        Agencia agencia = tabelaAgencia.getRegistros().get(row);
        /* Confirma a intenção de remover o objeto do banco de dados */
        if (GerarMensagens.confirmar(t, "Deseja excluir "
                + agencia.getNome() + "?")) {
            /* Executa a instrução de deletar do banco de dados*/
            var agenciaDao = new AgenciaDao();
            agenciaDao.deletar(agencia);
            GerarMensagens.alerta(t, "Removido com sucesso!");
        }
        pesquisar(t);
    }

}
