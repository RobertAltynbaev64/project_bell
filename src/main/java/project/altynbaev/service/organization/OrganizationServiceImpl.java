package project.altynbaev.service.organization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.altynbaev.dao.OrganizationDao;
import project.altynbaev.dto.organization.OrganizationFilterInDto;
import project.altynbaev.dto.organization.OrganizationFilterOutDto;
import project.altynbaev.dto.organization.OrganizationSaveDto;
import project.altynbaev.dto.organization.OrganizationUpdateAndGetDto;
import project.altynbaev.mapper.MapperFacade;
import project.altynbaev.model.Organization;

import java.util.List;

@Service
public class OrganizationServiceImpl implements OrganizationService {

    private final OrganizationDao organizationDao;
    private final MapperFacade mapperFacade;

    @Autowired
    public OrganizationServiceImpl(OrganizationDao organizationDao, MapperFacade mapperFacade) {
        this.organizationDao = organizationDao;
        this.mapperFacade = mapperFacade;
    }

    @Override
    public OrganizationUpdateAndGetDto findById(int id) {
        Organization organization = organizationDao.findById(id);
        OrganizationUpdateAndGetDto org = mapperFacade.map(organization, OrganizationUpdateAndGetDto.class);
        return org;
    }

    @Transactional
    @Override
    public void save(OrganizationSaveDto organizationSaveDTO) {
        Organization organization = mapperFacade.map(organizationSaveDTO, Organization.class);
        organizationDao.save(organization);
    }

    @Transactional
    @Override
    public void update(OrganizationUpdateAndGetDto organizationUpdateDTO) {
        Organization organization = mapperFacade.map(organizationUpdateDTO, Organization.class);
        organizationDao.update(organization, organizationUpdateDTO.getId());
    }


    @Transactional
    @Override
    public List<OrganizationFilterOutDto> filter(OrganizationFilterInDto organizationFilterDTO) {
        Organization organization = mapperFacade.map(organizationFilterDTO, Organization.class);
        List<Organization> list = organizationDao.filter(organization);
        return mapperFacade.mapAsList(list, OrganizationFilterOutDto.class);
    }
}
