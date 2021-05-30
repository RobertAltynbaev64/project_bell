package project.altynbaev.service.office;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.altynbaev.dao.OfficeDao;
import project.altynbaev.dao.OrganizationDao;
import project.altynbaev.dto.office.OfficeFilterInDto;
import project.altynbaev.dto.office.OfficeFilterOutDto;
import project.altynbaev.dto.office.OfficeSaveDto;
import project.altynbaev.dto.office.OfficeUpdateAndGetDto;
import project.altynbaev.mapper.MapperFacade;
import project.altynbaev.model.Office;

import java.util.List;

@Service
public class OfficeServiceImpl implements OfficeService {

    private final OfficeDao officeDao;
    private final MapperFacade mapperFacade;
    private final OrganizationDao organizationDao;

    @Autowired
    public OfficeServiceImpl(OfficeDao officeDao, MapperFacade mapperFacade, OrganizationDao organizationDao) {
        this.officeDao = officeDao;
        this.mapperFacade = mapperFacade;
        this.organizationDao = organizationDao;
    }

    @Override
    public OfficeUpdateAndGetDto findById(int id) {
        Office office = officeDao.findById(id);
        return mapperFacade.map(office, OfficeUpdateAndGetDto.class);
    }

    @Transactional
    @Override
    public void save(OfficeSaveDto officeSaveDTO) {
        Office office = mapperFacade.map(officeSaveDTO, Office.class);
        office.setOrganization(organizationDao.findById(officeSaveDTO.getOrgId()));
        officeDao.save(office);
    }

    @Transactional
    @Override
    public void update(OfficeUpdateAndGetDto officeUpdateAndGetDTO) {
        Office officefromDB = officeDao.findById(officeUpdateAndGetDTO.getId());
        Office office = mapperFacade.map(officeUpdateAndGetDTO, Office.class);
        officefromDB.setActive(office.isActive());
        officefromDB.setAddress(office.getAddress());
        officefromDB.setName(office.getName());
        officefromDB.setPhone(office.getPhone());
    }

    @Transactional
    @Override
    public List<OfficeFilterOutDto> filter(OfficeFilterInDto officeFilterInDTO) {
        Office office = mapperFacade.map(officeFilterInDTO, Office.class);
        office.setOrganization(organizationDao.findById(officeFilterInDTO.getOrgId()));
        List<Office> list = officeDao.filter(officeFilterInDTO);
        return mapperFacade.mapAsList(list, OfficeFilterOutDto.class);
    }

}
