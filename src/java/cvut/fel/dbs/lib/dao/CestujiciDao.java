package cvut.fel.dbs.lib.dao;

import cvut.fel.dbs.lib.model.Cestujici;

import javax.persistence.EntityManager;
import java.util.List;

public class CestujiciDao {
    private EntityManager em;

    public CestujiciDao() {
    }

    public CestujiciDao(EntityManager em) {
        this.em = em;
    }

    /**
     * @return all rows from table Cestujici
     */
    public List<Cestujici> findAll() {
        return em.createQuery("SELECT c FROM Cestujici c", Cestujici.class).getResultList();
    }

    /**
     * Creates record Cestujici in database
     *
     * @param c Cestujici to be created
     */
    public void create(Cestujici c) {
        em.persist(c);
    }

    /**
     * Finds target Cestujici based on his email in database
     *
     * @param email primary key from cestujici
     * @return found cestujici
     */
    public Cestujici find(String email) {
        return em.find(Cestujici.class, email);
    }

    /**
     * Updates target Cestujici in database
     *
     * @param c cestujici to be updated
     * @return
     */
    public Cestujici merge(Cestujici c) {
        return em.merge(c);
    }

    /**
     * Deletes target Cestujici from database
     *
     * @param c cestujici to be deleted
     */
    public void delete(Cestujici c) {
        em.remove(c);
    }
}
