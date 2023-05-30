package br.com.unincor.sistemabancario.model.dao;

import br.com.unincor.sistemabancario.connection.MySQL;
import br.com.unincor.sistemabancario.model.domain.Agencia;
import br.com.unincor.sistemabancario.model.domain.Cliente;
import br.com.unincor.sistemabancario.model.domain.Conta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ContaDao {
    
    public List<Conta> buscarTodasContas() {
        String sql = """
                     select contas.id, contas.cliente_id, contas.saldo,
                     	   contas.agencia_id, clientes.nome as nome_cliente,
                            clientes.cpf, clientes.email, 
                            clientes.telefone as telefone_cliente,
                            clientes.endereco as endereco_cliente, 
                            agencias.nome as nome_agencia, 
                            agencias.endereco as endereco_agencia,
                            agencias.telefone as telefone_agencia
                     from contas 
                     inner join clientes on clientes.id = contas.cliente_id
                     inner join agencias on agencias.id = contas.agencia_id
                     order by clientes.nome;
                     """;
        List<Conta> contas = new ArrayList<>();
        try(Connection con = MySQL.getConnection(); 
                PreparedStatement stmt = con.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                contas.add(construirContaResultSet(rs));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ContaDao.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return contas;
    }
    
    private Conta construirContaResultSet(ResultSet rs) throws SQLException {
        Cliente cliente = new Cliente();
        cliente.setId(rs.getInt("cliente_id"));
        cliente.setNome(rs.getString("nome_cliente"));
        cliente.setCpf(rs.getString("cpf"));
        cliente.setEmail(rs.getString("email"));
        cliente.setEndereco(rs.getString("endereco_cliente"));
        cliente.setTelefone(rs.getString("telefone_cliente"));
        
        Agencia agencia = new Agencia();
        agencia.setId(rs.getInt("agencia_id"));
        agencia.setNome(rs.getString("nome_agencia"));
        agencia.setEndereco(rs.getString("endereco_agencia"));
        agencia.setTelefone(rs.getString("telefone_agencia"));
        
        Conta conta = new Conta();
        conta.setId(rs.getInt("id"));
        conta.setCliente(cliente);
        conta.setAgencia(agencia);
        conta.setSaldo(rs.getDouble("saldo"));
        
        return conta;
    }
    
    public void inserir(Conta conta) {
        String sql = """
                     INSERT INTO CONTAS(CLIENTE_ID, AGENCIA_ID, SALDO)
                     VALUES (?, ?, ?);
                     """;
        try(Connection con = MySQL.getConnection();
                PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, conta.getCliente().getId());
            stmt.setInt(2, conta.getAgencia().getId());
            stmt.setDouble(3, conta.getSaldo());
            stmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ContaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void main(String[] args) {
        ContaDao contaDao = new ContaDao();
        List<Conta> contas = contaDao.buscarTodasContas();
        contas.forEach(System.out::println);
        
    }
}
