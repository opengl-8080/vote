package vote.infrastructure.db.jpa;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@ApplicationScoped
public class EntityManagerProvider {

    @Produces
    @PersistenceContext(unitName = "vote-db")
    private EntityManager em;
}
