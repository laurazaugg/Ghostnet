package ghostnet.service;

import ghostnet.model.Ghostnet;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class GhostnetService {
    @PersistenceContext(unitName = "ghostnetPU")
    private EntityManager em;

    @Transactional
    public void save(Ghostnet net) {
        em.persist(net);
    }
}
