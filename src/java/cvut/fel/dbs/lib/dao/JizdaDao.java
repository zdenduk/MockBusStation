package cvut.fel.dbs.lib.dao;

import cvut.fel.dbs.lib.model.Jizda;

import javax.persistence.EntityManager;
import java.util.List;

public class JizdaDao {
    private EntityManager em;

    public JizdaDao() {
    }

    public JizdaDao(EntityManager em) {
        this.em = em;
    }

    /**
     * @return all rows from table jizda
     */
    public List<Jizda> findAll() {
        return em.createQuery("SELECT j FROM Jizda j", Jizda.class).getResultList();
    }

    /**
     * Creates record Jizda in database
     *
     * @param j Jizda to be created
     */
    public void create(Jizda j) {
        em.persist(j);
    }

    /**
     * Finds target Jizda based on its id in database
     *
     * @param id primary key from Jizda
     * @return found Jizda
     */
    public Jizda find(Long id) {
        return em.find(Jizda.class, id);
    }

    /**
     * Updates target Jizda in database
     *
     * @param j Jizda to be updated
     * @return
     */
    public Jizda merge(Jizda j) {
        return em.merge(j);
    }

    /**
     * Deletes target Jizda
     *
     * @param j Jizda to be deleted
     */
    public void delete(Jizda j) {
        em.remove(j);
    }
}
