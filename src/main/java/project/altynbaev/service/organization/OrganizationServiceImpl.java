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
        Organization organizationFromDB = organizationDao.findById(organizationUpdateDTO.getId());
        organizationFromDB.setName(organization.getName());
        organizationFromDB.setFullName(organization.getFullName());
        organizationFromDB.setActive(organization.isActive());
        organizationFromDB.setAddress(organization.getAddress());
        organizationFromDB.setInn(organization.getInn());
        organizationFromDB.setPhone(organization.getPhone());
        organizationFromDB.setKpp(organization.getKpp());
    }

    @Transactional
    @Override
    public List<OrganizationFilterOutDto> filter(OrganizationFilterInDto organizationFilterDTO) {
        List<Organization> list = organizationDao.filter(organizationFilterDTO);
        return mapperFacade.mapAsList(list, OrganizationFilterOutDto.class);
    }
}
