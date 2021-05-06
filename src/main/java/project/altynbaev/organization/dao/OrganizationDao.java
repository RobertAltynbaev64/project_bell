package project.altynbaev.organization.dao;

import project.altynbaev.organization.model.Organization;
import project.altynbaev.organization.view.*;

import java.util.List;

/**
 * DAO для работы с Organization
 */
public interface OrganizationDao {
    /**
     * Получить список организаций по фильтру
     * @param organizationListReqView
     * @return
     */
    public List<OrganizationListRespView> getListByFilter(OrganizationListReqView organizationListReqView);

    /**
     * Получить организацию по id
     * @param id
     * @return
     */
    public OrganizationByIdRespView getOrganizationById(Long id);

    /**
     * Добавить организацию
     * @param organization
     */
    public void save(Organization organization);

    /**
     * Обновить организацию
     * @param organizationUpdateReqView
     */
    public void update(OrganizationUpdateReqView organizationUpdateReqView);
}
