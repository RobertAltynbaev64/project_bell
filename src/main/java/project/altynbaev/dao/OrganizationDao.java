package project.altynbaev.dao;

import project.altynbaev.dto.organization.OrganizationFilterInDto;
import project.altynbaev.model.Organization;

import java.util.List;

public interface OrganizationDao {

    Organization findById(int id);

    void save(Organization organization);

    void update(Organization organization, int id);

    List<Organization> filter(OrganizationFilterInDto organizationFilterInDto);
}
