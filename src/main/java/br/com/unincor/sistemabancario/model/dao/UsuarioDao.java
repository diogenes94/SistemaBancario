/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.unincor.sistemabancario.model.dao;

import br.com.unincor.sistemabancario.connection.MySQL;
import br.com.unincor.sistemabancario.model.domain.Usuario;
import br.com.unincor.sistemabancario.utils.Criptografar;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dioge
 */
public class UsuarioDao {
    
    public Usuario buildUsuarioResultSet(ResultSet rs) throws SQLException {
        return new Usuario(rs.getInt("id"), 
                rs.getString("nome"), 
                rs.getString("email"), rs.getString("senha"),
                rs.getDate("data_cadastro"));
    }
    
    public Optional<Usuario> getUsuarioPorEmail(String email) {
        String sql = "select * from usuarios where email = ?";
        
        try(Connection con = MySQL.getConnection(); 
                PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) {
                return Optional.of(buildUsuarioResultSet(rs));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return Optional.empty();
    }
    
    public void inserirUsuario(Usuario usuario) {
        var sql = "insert into usuarios(nome, email, senha) values (?, ?, ?)";
        try(Connection con = MySQL.getConnection(); 
                PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getSenha());
            stmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void main(String[] args) {
        Usuario usuario = new Usuario(null, "Diogenes",
                "diogenes@unincor.br",
                Criptografar.encryp("1234"),
                null);
        new UsuarioDao().inserirUsuario(usuario);
    }
}
