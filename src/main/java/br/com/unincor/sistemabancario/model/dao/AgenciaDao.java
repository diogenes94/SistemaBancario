package br.com.unincor.sistemabancario.model.dao;

import br.com.unincor.sistemabancario.connection.MySQL;
import br.com.unincor.sistemabancario.model.domain.Agencia;
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
public class AgenciaDao {
    
    public void inserir(Agencia agencia) {
        String sql = """
                     INSERT INTO AGENCIAS(NOME, ENDERECO, TELEFONE)
                     VALUES (?, ?, ?);
                     """;
        try(Connection con = MySQL.getConnection(); 
                PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, agencia.getNome());
            stmt.setString(2, agencia.getEndereco());
            stmt.setString(3, agencia.getTelefone());
            stmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(AgenciaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<Agencia> buscarTodasAgencias() {
        String sql = """
                     SELECT * FROM AGENCIAS
                     """;
        List<Agencia> agencias = new ArrayList<>(); /* Cria uma lista vazia*/
        try(Connection con = MySQL.getConnection(); 
                PreparedStatement stmt = con.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery(); /* Executa a instrução */
            while(rs.next()) {
                var agencia = new Agencia(); /* Cria uma nova agência */
                agencia.setId(rs.getInt("id"));
                agencia.setNome(rs.getString("nome"));
                agencia.setEndereco(rs.getString("endereco"));
                agencia.setTelefone(rs.getString("telefone"));
                agencias.add(agencia); /* Coloca a agência em uma lista */
            }
        } catch (SQLException ex) {
            Logger.getLogger(AgenciaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return agencias; /* Retorna a lista */
    }
    
    public int deletar(Integer id) {
        String sql = """
                     DELETE FROM AGENCIAS WHERE ID = ?;
                     """;
        try(Connection con = MySQL.getConnection(); 
                PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AgenciaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
    public static void main(String[] args) {
        var agenciaDao = new AgenciaDao();
        
//        Agencia ag1 = new Agencia(null, "BB", "Três Corações, Centro",
//                "1234567");        
//        agenciaDao.inserir(ag1);
        agenciaDao.buscarTodasAgencias().forEach(System.out::println);
        
    }
    
}
