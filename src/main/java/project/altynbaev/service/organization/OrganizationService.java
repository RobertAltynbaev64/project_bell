package project.altynbaev.service.organization;

import project.altynbaev.dto.organization.OrganizationFilterInDto;
import project.altynbaev.dto.organization.OrganizationFilterOutDto;
import project.altynbaev.dto.organization.OrganizationSaveDto;
import project.altynbaev.dto.organization.OrganizationUpdateAndGetDto;

import javax.validation.Valid;
import java.util.List;

public interface OrganizationService {

    OrganizationUpdateAndGetDto findById(int id);

    void save(@Valid OrganizationSaveDto organizationSaveDTO);

    void update(@Valid OrganizationUpdateAndGetDto organizationUpdateDTO);

    List<OrganizationFilterOutDto> filter(@Valid OrganizationFilterInDto organizationFilterDTO);

}
