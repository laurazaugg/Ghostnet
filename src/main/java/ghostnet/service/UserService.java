package ghostnet.service;

import ghostnet.model.User;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

@ApplicationScoped
public class UserService {

    @PersistenceContext
    private EntityManager em;

    public User findByUsernameAndPassword(String username, String password) {
        List<User> result = em.createQuery(
                        "SELECT u FROM User u WHERE u.username = :username AND u.password = :password", User.class)
                .setParameter("username", username)
                .setParameter("password", password)
                .getResultList();

        return result.isEmpty() ? null : result.get(0);
    }
}
