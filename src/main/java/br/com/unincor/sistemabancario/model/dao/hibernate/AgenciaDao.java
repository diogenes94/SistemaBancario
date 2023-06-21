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
    
    public List<Agencia> buscarAgenciasPorNome(String nome) {
        String sql = "from Agencia where nome like :nome";
        Query query = getEntityManager().createQuery(sql,
                Agencia.class);
        query.setParameter("nome", "%" + nome + "%");
        return query.getResultList();
    }

    public static void main(String[] args) {
        //new AgenciaDao().buscarTudo().forEach(System.out::println);
        AgenciaDao agenciaDao = new AgenciaDao();
        agenciaDao.buscarAgenciasPorNome("Norte")
                .forEach(System.out::println);
    }

}
