package project.altynbaev.service.office;

import project.altynbaev.dto.office.OfficeFilterInDto;
import project.altynbaev.dto.office.OfficeFilterOutDto;
import project.altynbaev.dto.office.OfficeSaveDto;
import project.altynbaev.dto.office.OfficeUpdateAndGetDto;

import javax.validation.Valid;
import java.util.List;

public interface OfficeService {

    OfficeUpdateAndGetDto findById(int id);

    void save(@Valid OfficeSaveDto officeSaveDTO);

    void update(@Valid OfficeUpdateAndGetDto officeUpdateAndGetDTO);

    List<OfficeFilterOutDto> filter(@Valid OfficeFilterInDto officeFilterInDTO);

}
