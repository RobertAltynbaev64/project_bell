package project.altynbaev.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import project.altynbaev.dto.office.OfficeFilterInDto;
import project.altynbaev.dto.office.OfficeFilterOutDto;
import project.altynbaev.dto.office.OfficeSaveDto;
import project.altynbaev.dto.office.OfficeUpdateAndGetDto;
import project.altynbaev.service.office.OfficeService;

import java.util.List;

@RestController
@RequestMapping("office")
public class OfficeController {

    private final OfficeService officeService;

    @Autowired
    public OfficeController(OfficeService officeService) {
        this.officeService = officeService;
    }

    @PostMapping("list")
    public List<OfficeFilterOutDto> filter(@RequestBody OfficeFilterInDto officeFilterInDTO) {
        return officeService.filter(officeFilterInDTO);
    }

    @GetMapping("{id}")
    public OfficeUpdateAndGetDto getOffice(@PathVariable int id) {
        return officeService.findById(id);
    }

    @PostMapping("save")
    public void saveOffice(@RequestBody OfficeSaveDto officeSaveDTO) {
        officeService.save(officeSaveDTO);
    }

    @PostMapping("update")
    public void updateOffice(@RequestBody OfficeUpdateAndGetDto officeUpdateAndGetDTO) {
        officeService.update(officeUpdateAndGetDTO);
    }
}
