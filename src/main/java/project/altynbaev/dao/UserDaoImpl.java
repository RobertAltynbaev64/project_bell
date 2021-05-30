package project.altynbaev.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import project.altynbaev.model.User;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.util.ArrayList;
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
    public List<User> filter(project.altynbaev.dto.user.UserFilterInDto user) {

        Predicate predicate;
        List<Predicate> list = new ArrayList<>();

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> orgCriteria = cb.createQuery(User.class);
        Root<User> orgRoot = orgCriteria.from(User.class);
        orgCriteria.select(orgRoot);

        predicate = cb.equal(orgRoot.get("office").get("id"), user.getOfficeId());
        list.add(predicate);

        if (user.getFirstName() != null) {
            predicate = cb.equal(orgRoot.get("firstName"), user.getFirstName());
            list.add(predicate);
        }
        if (user.getSecondName() != null) {
            predicate = cb.equal(orgRoot.get("secondName"), user.getSecondName());
            list.add(predicate);
        }
        if (user.getMiddleName() != null) {
            predicate = cb.equal(orgRoot.get("middleName"), user.getMiddleName());
            list.add(predicate);
        }
        if (user.getPosition() != null) {
            predicate = cb.equal(orgRoot.get("position"), user.getPosition());
            list.add(predicate);
        }
        if (user.getDocCode() != 0) {
            predicate = cb.equal(orgRoot.get("document").get("doc").get("docCode"), user.getDocCode());
            list.add(predicate);
        }
        if (user.getCitizenshipCode() != 0) {
            predicate = cb.equal(orgRoot.get("country").get("countryCode"), user.getCitizenshipCode());
            list.add(predicate);
        }

        Predicate[] pr = new Predicate[list.size()];
        list.toArray(pr);
        orgCriteria.where(cb.and(pr));

        return em.createQuery(orgCriteria).getResultList();
    }
}
