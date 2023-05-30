/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.unincor.sistemabancario.model.dao;

import br.com.unincor.sistemabancario.connection.MySQL;
import br.com.unincor.sistemabancario.model.domain.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dioge
 */
public class ClienteDao {

    public List<Cliente> buscarTodosClientes() {
        String sql = "Select * from clientes order by nome";
        List<Cliente> clientes = new ArrayList<>();
        try (Connection con = MySQL.getConnection(); 
                PreparedStatement stmt = con.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                clientes.add(getClienteResult(rs));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return clientes;
    }

    private Cliente getClienteResult(ResultSet rs) throws SQLException {
        return new Cliente(rs.getInt("id"),
                rs.getString("nome"),
                rs.getString("cpf"),
                rs.getString("email"),
                rs.getString("telefone"),
                rs.getString("endereco"));
    }
}
