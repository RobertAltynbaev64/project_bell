package project.altynbaev.dictionary.country.dao;

import org.springframework.stereotype.Repository;
import project.altynbaev.dictionary.country.model.Country;
import project.altynbaev.dictionary.country.view.CountryView;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class CountryDaoImpl implements CountryDao {
    private final EntityManager em;

    public CountryDaoImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public Country getCountryByCode(Integer countryCode) {
        if (countryCode == null) return null;
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Country> criteriaQuery = criteriaBuilder.createQuery(Country.class); // какого типа возвращаются
        Root<Country> documentType = criteriaQuery.from(Country.class); // откуда берем

        criteriaQuery.where(
                criteriaBuilder.equal(documentType.get("countryCode"), countryCode)
        );

        criteriaQuery.select(documentType);

        TypedQuery<Country> query = em.createQuery(criteriaQuery);
        List<Country> documentTypes = query.getResultList();

        return documentTypes.size() > 0 ? documentTypes.get(0) : null;
    }

    @Override
    public Country getCountryByName(String countryName) {
        if (countryName == null) return null;
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Country> criteriaQuery = criteriaBuilder.createQuery(Country.class); // какого типа возвращаются
        Root<Country> documentType = criteriaQuery.from(Country.class); // откуда берем

        criteriaQuery.where(
                criteriaBuilder.equal(documentType.get("countryName"), countryName)
        );

        criteriaQuery.select(documentType);

        TypedQuery<Country> query = em.createQuery(criteriaQuery);
        List<Country> documentTypes = query.getResultList();

        return documentTypes.size() > 0 ? documentTypes.get(0) : null;
    }

    @Override
    public List<CountryView> getList() {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<CountryView> criteriaQuery = criteriaBuilder.createQuery(CountryView.class); // какого типа возвращаются
        Root<Country> documentType = criteriaQuery.from(Country.class); // откуда берем

        criteriaQuery.multiselect(
                documentType.get("countryName"),
                documentType.get("countryCode")
        );
        TypedQuery<CountryView> query = em.createQuery(criteriaQuery);
        return query.getResultList();
    }
}
