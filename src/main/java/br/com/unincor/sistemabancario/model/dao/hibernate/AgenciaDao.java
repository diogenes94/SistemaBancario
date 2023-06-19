package br.com.unincor.sistemabancario.model.dao.hibernate;

import br.com.unincor.sistemabancario.connection.HibernateUtil;
import br.com.unincor.sistemabancario.model.domain.Agencia;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author dioge
 */
public class AgenciaDao extends GenericDao<Integer, Agencia>{

    public static void main(String[] args) {
        //new AgenciaDao().buscarTudo().forEach(System.out::println);
        AgenciaDao agenciaDao = new AgenciaDao();
        System.out.println(agenciaDao.buscarPorId(2));
        
//        agenciaDao.deletar(value);
    }

}
