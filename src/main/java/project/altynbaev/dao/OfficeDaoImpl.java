package project.altynbaev.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import project.altynbaev.model.Office;
import project.altynbaev.dto.office.OfficeFilterInDto;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
public class OfficeDaoImpl implements OfficeDao{

    private final EntityManager em;

    @Autowired
    public OfficeDaoImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Office> filter(OfficeFilterInDto office) {

        Predicate predicate;
        List<Predicate> list = new ArrayList<>();

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Office> orgCriteria = cb.createQuery(Office.class);
        Root<Office> orgRoot = orgCriteria.from(Office.class);
        orgCriteria.select(orgRoot);

        predicate = cb.equal(orgRoot.get("organization").get("id"), office.getOrgId());
        list.add(predicate);

        if(office.getName() != null) {
            predicate = cb.equal(orgRoot.get("name"), office.getName());
            list.add(predicate);
        }

        if(office.getPhone() != null) {
            predicate = cb.equal(orgRoot.get("phone"), office.getPhone());
            list.add(predicate);
        }

        if(office.isActive() != null) {
            predicate = cb.equal(orgRoot.get("isActive"), office.isActive());
            list.add(predicate);
        }

        Predicate[] pr = new Predicate[list.size()];
        list.toArray(pr);
        orgCriteria.where(cb.and(pr));

        return em.createQuery(orgCriteria).getResultList();
    }

    @Override
    public Office findById(int id) {
        return em.find(Office.class, id);
    }

    @Override
    public void save(Office office) {
        em.persist(office);
    }

    @Override
    public void update(Office office, int id) {
        Office officefromDB = em.find(Office.class, id);
        officefromDB.setActive(office.isActive());
        officefromDB.setAddress(office.getAddress());
        officefromDB.setName(office.getName());
        officefromDB.setPhone(office.getPhone());
    }
}
