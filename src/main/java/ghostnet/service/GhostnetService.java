package ghostnet.service;

import ghostnet.model.Ghostnet;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class GhostnetService {
    @PersistenceContext(unitName = "ghostnetPU")
    private EntityManager em;

    @Transactional
    public void save(Ghostnet net) {
        em.persist(net);
    }

    public List<Ghostnet> findAllOpenNets() {
        return em.createQuery(
                        "SELECT g FROM Ghostnet g",
                        Ghostnet.class)
                .getResultList();
    }

    @Transactional
    public void update(Ghostnet net) {
        em.merge(net);
    }

}
