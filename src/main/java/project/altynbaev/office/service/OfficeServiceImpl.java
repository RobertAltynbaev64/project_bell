package project.altynbaev.office.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.altynbaev.exception.NotFoundException;
import project.altynbaev.office.dao.OfficeDao;
import project.altynbaev.office.model.Office;
import project.altynbaev.office.view.OfficeByIdRespView;
import project.altynbaev.office.view.OfficeListReqView;
import project.altynbaev.office.view.OfficeListRespView;
import project.altynbaev.office.view.OfficeSaveReqView;
import project.altynbaev.office.view.OfficeUpdateReqView;
import project.altynbaev.organization.service.OrganizationService;

import java.util.List;

/**
 * {@inheritDoc}
 */
@Service
public class OfficeServiceImpl implements OfficeService {
    private static final Logger LOGGER = LoggerFactory.getLogger(OfficeServiceImpl.class);

    private OfficeDao dao;

    @Autowired
    private OrganizationService organizationService;

    @Autowired
    public OfficeServiceImpl(OfficeDao dao) {
        this.dao = dao;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public List<OfficeListRespView> getOfficeByFilter(OfficeListReqView officeListReqView) {
        return dao.getListByFilter(officeListReqView);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public OfficeByIdRespView getOfficeById(Long id) {
        if (dao.getOfficeById(id) == null) {
            throw new NotFoundException("Офис с таким id не найден");
        } else {
            return dao.getOfficeById(id);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void saveOffice(OfficeSaveReqView officeSaveReqView) {
        try {
            organizationService.getOrganizationById(officeSaveReqView.orgId);
        } catch (NotFoundException ex) {
            throw ex;
        }

        Office office = new Office
                (
                        officeSaveReqView.orgId,
                        officeSaveReqView.name,
                        officeSaveReqView.address,
                        officeSaveReqView.phone,
                        officeSaveReqView.isActive
                );

        dao.save(office);
        LOGGER.debug("Новый офис добавлен");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void updateOffice(OfficeUpdateReqView officeUpdateReqView) {
        LOGGER.debug("Офис с id={} изменен", officeUpdateReqView.id);
        dao.update(officeUpdateReqView);
    }
}
