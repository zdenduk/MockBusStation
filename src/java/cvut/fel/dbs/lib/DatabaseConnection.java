package cvut.fel.dbs.lib;

import cvut.fel.dbs.lib.dao.CestujiciDao;
import cvut.fel.dbs.lib.dao.JizdaDao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * This class establishes connection with database
 */
public class DatabaseConnection {

    private static DatabaseConnection instance = null;

    private EntityManager em;
    private CestujiciDao cestujiciDao;
    private JizdaDao jizdaDao;

    /**
     * Setups EntityManager, CestujiciDao and JizdaDao
     */
    private void setup() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        em = emf.createEntityManager();
        cestujiciDao = new CestujiciDao(em);
        jizdaDao = new JizdaDao(em);
    }

    /**
     * @return instance of DatabaseConnection
     */
    public static DatabaseConnection getInstance() {
        if (instance == null)
            instance = new DatabaseConnection();
        return instance;
    }

    private DatabaseConnection() {
        setup();
    }

    public EntityManager getEm() {
        return em;
    }

    public CestujiciDao getCestujiciDao() {
        return cestujiciDao;
    }

    public JizdaDao getJizdaDao() {
        return jizdaDao;
    }
}
