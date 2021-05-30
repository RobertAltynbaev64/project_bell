package project.altynbaev.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import project.altynbaev.dto.organization.OrganizationFilterInDto;
import project.altynbaev.model.Organization;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
public class OrganizationDaoImpl implements OrganizationDao {

    private final EntityManager em;

    @Autowired
    OrganizationDaoImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public Organization findById(int id) {
        return em.find(Organization.class, id);
    }

    @Override
    public void save(Organization organization) {
        em.persist(organization);
    }

    @Override
    public void update(Organization organization, int id) {
        Organization organizationFromDB = em.find(Organization.class, id);
        organizationFromDB.setName(organization.getName());
        organizationFromDB.setFullName(organization.getFullName());
        organizationFromDB.setActive(organization.isActive());
        organizationFromDB.setAddress(organization.getAddress());
        organizationFromDB.setInn(organization.getInn());
        organizationFromDB.setPhone(organization.getPhone());
        organizationFromDB.setKpp(organization.getKpp());
    }

    @Override
    public List<Organization> filter(OrganizationFilterInDto organization) {

        Predicate predicate;
        List<Predicate> list = new ArrayList<>();

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Organization> orgCriteria = cb.createQuery(Organization.class);
        Root<Organization> orgRoot = orgCriteria.from(Organization.class);
        orgCriteria.select(orgRoot);

        predicate = cb.equal(orgRoot.get("name"), organization.getName());
        list.add(predicate);

        if (organization.getInn() != null) {
            predicate = cb.equal(orgRoot.get("inn"), organization.getInn());
            list.add(predicate);
        }
        if (organization.isActive() != null) {
            predicate = cb.equal(orgRoot.get("isActive"), organization.isActive());
            list.add(predicate);
        }

        Predicate[] pr = new Predicate[list.size()];
        list.toArray(pr);
        orgCriteria.where(cb.and(pr));

        return em.createQuery(orgCriteria).getResultList();
    }
}
