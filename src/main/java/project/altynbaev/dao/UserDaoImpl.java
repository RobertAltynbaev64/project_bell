package project.altynbaev.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import project.altynbaev.model.User;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    private final EntityManager em;

    @Autowired
    public UserDaoImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public User findById(int id) {
        return em.find(User.class, id);
    }

    @Override
    public void save(User user) {
        em.persist(user);
    }

    @Override
    public void update(User user, int id) {
        User userFromDB = em.find(User.class, id);
        userFromDB.setOffice(user.getOffice());
        userFromDB.setFirstName(user.getFirstName());
        userFromDB.setSecondName(user.getSecondName());
        userFromDB.setMiddleName(user.getMiddleName());
        userFromDB.setPosition(user.getPosition());
        userFromDB.setPhone(user.getPhone());
        userFromDB.setIdentified(user.isIdentified());
    }

    @Override
    public List<User> filter(User user, int docCode) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> orgCriteria = cb.createQuery(User.class);
        Root<User> orgRoot = orgCriteria.from(User.class);
        orgCriteria.select(orgRoot);

        if (user.getFirstName() != null && user.getSecondName() != null && user.getMiddleName() != null
                && user.getPosition() != null && docCode != 0 && user.getCountry() != null) {
            orgCriteria.where(cb.and(cb.equal(orgRoot.get("office"), user.getOffice()),
                    cb.equal(orgRoot.get("firstName"), user.getFirstName()),
                    cb.equal(orgRoot.get("secondName"), user.getSecondName()),
                    cb.equal(orgRoot.get("middleName"), user.getMiddleName()),
                    cb.equal(orgRoot.get("position"), user.getPosition()),
                    cb.equal(orgRoot.get("document").get("doc").get("docCode"), docCode),
                    cb.equal(orgRoot.get("country"), user.getCountry())));
        } else if (user.getFirstName() != null && user.getSecondName() != null && user.getMiddleName() != null
                && user.getPosition() != null && docCode != 0) {
            orgCriteria.where(cb.and(cb.equal(orgRoot.get("office"), user.getOffice()),
                    cb.equal(orgRoot.get("firstName"), user.getFirstName()),
                    cb.equal(orgRoot.get("secondName"), user.getSecondName()),
                    cb.equal(orgRoot.get("middleName"), user.getMiddleName()),
                    cb.equal(orgRoot.get("position"), user.getPosition()),
                    cb.equal(orgRoot.get("document").get("doc").get("docCode"), docCode)));
        } else if (user.getFirstName() != null && user.getSecondName() != null && user.getMiddleName() != null
                && user.getPosition() != null && user.getCountry() != null) {
            orgCriteria.where(cb.and(cb.equal(orgRoot.get("office"), user.getOffice()),
                    cb.equal(orgRoot.get("firstName"), user.getFirstName()),
                    cb.equal(orgRoot.get("secondName"), user.getSecondName()),
                    cb.equal(orgRoot.get("middleName"), user.getMiddleName()),
                    cb.equal(orgRoot.get("position"), user.getPosition()),
                    cb.equal(orgRoot.get("country"), user.getCountry())));
        } else if (user.getFirstName() == null && user.getSecondName() == null && user.getMiddleName() == null
                && user.getPosition() == null && docCode == 0 && user.getCountry() == null) {
            orgCriteria.where(cb.equal(orgRoot.get("office"), user.getOffice()));
        }

        return em.createQuery(orgCriteria).getResultList();
    }
}
