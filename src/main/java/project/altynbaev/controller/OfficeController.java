package project.altynbaev.controller;

import project.altynbaev.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import project.altynbaev.dto.office.OfficeFilterInDto;
import project.altynbaev.dto.office.OfficeFilterOutDto;
import project.altynbaev.dto.office.OfficeSaveDto;
import project.altynbaev.dto.office.OfficeUpdateAndGetDto;
import project.altynbaev.service.office.OfficeService;

import javax.validation.Valid;
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
    public List<OfficeFilterOutDto> filter(@RequestBody @Valid OfficeFilterInDto officeFilterInDTO){
        return officeService.filter(officeFilterInDTO);
    }

    @GetMapping("{id}")
    public OfficeUpdateAndGetDto getOffice(@PathVariable int id){
        if(officeService.findById(id) != null){
            return officeService.findById(id);
        }
        else throw new NotFoundException("Не найден офис с id = " + id);
    }

    @PostMapping("save")
    public void saveOffice(@RequestBody @Valid OfficeSaveDto officeSaveDTO){
        officeService.save(officeSaveDTO);
    }

    @PostMapping("update")
    public void updateOffice(@RequestBody @Valid OfficeUpdateAndGetDto officeUpdateAndGetDTO){
        officeService.update(officeUpdateAndGetDTO);
    }
}
