package project.altynbaev.office.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import project.altynbaev.office.model.Office;
import project.altynbaev.office.view.OfficeByIdRespView;
import project.altynbaev.office.view.OfficeListReqView;
import project.altynbaev.office.view.OfficeListRespView;
import project.altynbaev.office.view.OfficeUpdateReqView;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * {@inheritDoc}
 */
@Repository
public class OfficeDaoImpl implements OfficeDao {
    private final EntityManager em;

    @Autowired
    public OfficeDaoImpl(EntityManager em) {
        this.em = em;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<OfficeListRespView> getListByFilter(OfficeListReqView officeListReqView) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<OfficeListRespView> criteriaQuery = criteriaBuilder.createQuery(OfficeListRespView.class); // какого типа возвращаются
        Root<Office> office = criteriaQuery.from(Office.class); // откуда берем

        criteriaQuery.select(criteriaBuilder.construct(OfficeListRespView.class, office.get("id"), office.get("name"), office.get("isActive"))); // выборка определенных полей

        // построение динамического запроса
        List<Predicate> allPredicates = new ArrayList<>(); // список фильтров
        allPredicates.add(criteriaBuilder.equal(office.get("orgId"), officeListReqView.orgId)); // обязательный фильтр по name

        if (officeListReqView.name != null) {
            allPredicates.add(criteriaBuilder.equal(office.get("name"), officeListReqView.name)); // обязательный фильтр по name
        }
        if (officeListReqView.phone != null) {
            allPredicates.add(criteriaBuilder.equal(office.get("phone"), officeListReqView.phone)); // необязательный фильтр по inn
        }
        if (officeListReqView.isActive != null) {
            allPredicates.add(criteriaBuilder.equal(office.get("isActive"), officeListReqView.isActive)); // необязательный фильтр по isActive
        }

        criteriaQuery.where(
                criteriaBuilder.and(
                        allPredicates.toArray(new Predicate[0])
                )
        );

        TypedQuery<OfficeListRespView> query = em.createQuery(criteriaQuery);
        List<OfficeListRespView> officeList = query.getResultList();

        return officeList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public OfficeByIdRespView getOfficeById(Long id) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<OfficeByIdRespView> criteriaQuery = criteriaBuilder.createQuery(OfficeByIdRespView.class); // какого типа возвращаются
        Root<Office> office = criteriaQuery.from(Office.class); // откуда берем

        criteriaQuery.multiselect(
                office.get("id"),
                office.get("name"),
                office.get("address"),
                office.get("phone"),
                office.get("isActive")
        );

        criteriaQuery.where(
                criteriaBuilder.equal(office.get("id"), id)
        );

        TypedQuery<OfficeByIdRespView> query = em.createQuery(criteriaQuery);
        List<OfficeByIdRespView> officeByIdRespView = query.getResultList();

        return query.getResultList().size() > 0 ? officeByIdRespView.get(0) : null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(Office office) {
        em.persist(office);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(OfficeUpdateReqView officeUpdateReqView) {
        Office office = em.find(Office.class, officeUpdateReqView.id);

        office.setName(officeUpdateReqView.name);
        office.setAddress(officeUpdateReqView.address);

        if (officeUpdateReqView.phone != null) {
            office.setPhone(officeUpdateReqView.phone);
        }
        if (officeUpdateReqView.isActive != null) {
            office.setActive(officeUpdateReqView.isActive);
        }
    }
}
