package project.altynbaev.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import project.altynbaev.dto.organization.OrganizationFilterInDto;
import project.altynbaev.model.Organization;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
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
    public List<Organization> filter(OrganizationFilterInDto organization) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Organization> orgCriteria = cb.createQuery(Organization.class);
        Root<Organization> orgRoot = orgCriteria.from(Organization.class);
        orgCriteria.select(orgRoot);
        if (organization.getInn() != null && organization.isActive() != null) {
            orgCriteria.where(cb.and(cb.equal(orgRoot.get("name"), organization.getName()),
                    cb.equal(orgRoot.get("inn"), organization.getInn()),
                    cb.equal(orgRoot.get("isActive"), organization.isActive())));
        } else if (organization.getInn() != null) {
            orgCriteria.where(cb.and(cb.equal(orgRoot.get("name"), organization.getName()),
                    cb.equal(orgRoot.get("inn"), organization.getInn())));
        } else if (organization.isActive() != null) {
            orgCriteria.where(cb.and(cb.equal(orgRoot.get("name"), organization.getName()),
                    cb.equal(orgRoot.get("isActive"), organization.isActive())));
        } else {
            orgCriteria.where(cb.and(cb.equal(orgRoot.get("name"), organization.getName())));
        }
        return em.createQuery(orgCriteria).getResultList();
    }
}
