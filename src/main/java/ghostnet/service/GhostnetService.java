package ghostnet.service;

import ghostnet.model.Ghostnet;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@ApplicationScoped
public class GhostnetService {
    @PersistenceContext
    private EntityManager em;

    public void save(Ghostnet net) {
        em.persist(net);
    }
}
