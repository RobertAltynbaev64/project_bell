package project.altynbaev.user.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import project.altynbaev.exception.NotFoundException;
import project.altynbaev.dictionary.country.model.Country;
import project.altynbaev.dictionary.country.service.CountryService;
import project.altynbaev.dictionary.doc.model.Document;
import project.altynbaev.dictionary.doc.model.DocumentType;
import project.altynbaev.dictionary.doc.service.DocService;
import project.altynbaev.office.service.OfficeService;
import project.altynbaev.office.view.OfficeByIdRespView;
import project.altynbaev.user.model.User;
import project.altynbaev.user.view.*;

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
public class UserDaoImpl implements UserDao {
    private final EntityManager em;

    @Autowired
    private DocService docService;

    @Autowired
    private CountryService countryService;

    @Autowired
    private OfficeService officeService;

    public UserDaoImpl(EntityManager em) {
        this.em = em;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UserListRespView> getListByFilter(UserListReqView userListReqView) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<UserListRespView> criteriaQuery = criteriaBuilder.createQuery(UserListRespView.class); // какого типа возвращаются
        Root<User> user = criteriaQuery.from(User.class); // откуда берем

        criteriaQuery.select(criteriaBuilder.construct(UserListRespView.class, user.get("id"), user.get("firstName"), user.get("secondName"), user.get("middleName"), user.get("position"))); // выборка определенных полей

        // построение динамического запроса
        List<Predicate> allPredicates = new ArrayList<>(); // список фильтров
        allPredicates.add(criteriaBuilder.equal(user.get("officeId"), userListReqView.officeId)); // обязательный фильтр

        if (userListReqView.firstName != null) {
            allPredicates.add(criteriaBuilder.equal(user.get("firstName"), userListReqView.firstName)); // необязательный фильтр
        }
        if (userListReqView.secondName != null) {
            allPredicates.add(criteriaBuilder.equal(user.get("secondName"), userListReqView.secondName)); // необязательный фильтр
        }
        if (userListReqView.middleName != null) {
            allPredicates.add(criteriaBuilder.equal(user.get("middleName"), userListReqView.middleName)); // необязательный фильтр
        }
        if (userListReqView.position != null) {
            allPredicates.add(criteriaBuilder.equal(user.get("position"), userListReqView.position)); // необязательный фильтр
        }

        criteriaQuery.where(
                criteriaBuilder.and(
                        allPredicates.toArray(new Predicate[0])
                )
        );

        TypedQuery<UserListRespView> query = em.createQuery(criteriaQuery);
        List<UserListRespView> userList = query.getResultList();

        return userList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserByIdRespView getUserById(Long id) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<UserByIdRespView> criteriaQuery = criteriaBuilder.createQuery(UserByIdRespView.class); // какого типа возвращаются
        Root<User> user = criteriaQuery.from(User.class); // откуда берем
        Root<Document> document = criteriaQuery.from(Document.class); // откуда берем
        Root<DocumentType> documentType = criteriaQuery.from(DocumentType.class); // откуда берем
        Root<Country> country = criteriaQuery.from(Country.class); // откуда берем

        criteriaQuery.multiselect(
                user.get("id"),
                user.get("firstName"),
                user.get("secondName"),
                user.get("middleName"),
                user.get("position"),
                user.get("phone"),
                documentType.get("docName"),
                document.get("docNumber"),
                document.get("docDate"),
                country.get("countryName"),
                country.get("countryCode"),
                user.get("isIdentified")
        );

        criteriaQuery.where(
                criteriaBuilder.equal(document.get("userId"), user.get("id")),
                criteriaBuilder.equal(document.get("documentType"), documentType.get("id")),
                criteriaBuilder.equal(country.get("id"), user.get("country")),
                criteriaBuilder.equal(user.get("id"), id)
        );

        TypedQuery<UserByIdRespView> query = em.createQuery(criteriaQuery);
        List<UserByIdRespView> userByIdRespView = query.getResultList();

        return userByIdRespView.size() > 0 ? userByIdRespView.get(0) : null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(User user) {
        em.persist(user);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(UserUpdateReqView userUpdateReqView) {
        updateUser(userUpdateReqView);
        updateDocument(userUpdateReqView);
    }

    private void updateUser(UserUpdateReqView userUpdateReqView) {
        OfficeByIdRespView officeId;
        if (userUpdateReqView.officeId != null) {
            try {
                officeService.getOfficeById(userUpdateReqView.officeId);
            } catch (NotFoundException ex) {
                throw ex;
            }
            officeId = officeService.getOfficeById(userUpdateReqView.officeId);
        } else {
            officeId = null;
        }

        Country countryByCode = countryService.getCountryByCode(userUpdateReqView.citizenshipCode);
        if (userUpdateReqView.citizenshipCode != null && countryByCode == null) {
            throw new NotFoundException("Страна с таким кодом не найдена");
        }

        User user = em.find(User.class, userUpdateReqView.id);

        // изменение обязательных параметров запроа
        user.setFirstName(userUpdateReqView.firstName);
        user.setPosition(userUpdateReqView.position);

        // необязательные параметры, изменяем только, если они указаны
        if (officeId != null) {
            user.setOfficeId(userUpdateReqView.officeId);
        }
        if (userUpdateReqView.secondName != null) {
            user.setSecondName(userUpdateReqView.secondName);
        }
        if (userUpdateReqView.middleName != null) {
            user.setMiddleName(userUpdateReqView.middleName);
        }
        if (userUpdateReqView.phone != null) {
            user.setPhone(userUpdateReqView.phone);
        }
        if (countryByCode != null) {
            user.setCountry(countryByCode);
        }
        if (userUpdateReqView.isIdentified != null) {
            user.setIdentified(userUpdateReqView.isIdentified);
        }
    }

    private void updateDocument(UserUpdateReqView userUpdateReqView) {
        DocumentType documentType = docService.getDocByName(userUpdateReqView.docName);
        if (userUpdateReqView.docName != null && documentType == null) {
            throw new NotFoundException("Документ с таким именем не найден");
        }

        Document document = em.find(Document.class, userUpdateReqView.id);

        // необязательные параметры, изменяем только, если они указаны
        if (documentType != null) {
            document.setDocumentType(documentType);
        }
        if (userUpdateReqView.docNumber != null) {
            document.setDocNumber(userUpdateReqView.docNumber);
        }
        if (userUpdateReqView.docDate != null) {
            document.setDocDate(userUpdateReqView.docDate);
        }
    }
}
