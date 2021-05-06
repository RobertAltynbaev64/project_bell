package project.altynbaev.organization.service;

import project.altynbaev.organization.view.*;

import java.util.List;

public interface OrganizationService {

    /**
     * Получить список организаций по фильтру
     * @param organizationListReqView
     * @return
     */
    public List<OrganizationListRespView> getOrganizationByFilter(OrganizationListReqView organizationListReqView);

    /**
     * Получить организацию по id
     * @param id
     * @return
     */
    public OrganizationByIdRespView getOrganizationById(Long id);

    /**
     * Добавить организацию
     * @param organizationSaveReqView
     */
    public void saveOrganization(OrganizationSaveReqView organizationSaveReqView);

    /**
     * Обновить организацию
     * @param organizationUpdateReqView
     */
    public void updateOrganization(OrganizationUpdateReqView organizationUpdateReqView);
}
