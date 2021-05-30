package project.altynbaev.controller;

import project.altynbaev.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import project.altynbaev.dto.organization.OrganizationFilterInDto;
import project.altynbaev.dto.organization.OrganizationFilterOutDto;
import project.altynbaev.dto.organization.OrganizationSaveDto;
import project.altynbaev.dto.organization.OrganizationUpdateAndGetDto;
import project.altynbaev.service.organization.OrganizationService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("organization")
public class OrganizationController {

    private final OrganizationService organizationService;

    @Autowired
    public OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    @PostMapping("list")
    public List<OrganizationFilterOutDto> filter(@RequestBody @Valid OrganizationFilterInDto organizationFilterInDTO) {
        return organizationService.filter(organizationFilterInDTO);
    }

    @GetMapping("{id}")
    public OrganizationUpdateAndGetDto getOrg(@PathVariable int id) {
        if (organizationService.findById(id) != null) {
            return organizationService.findById(id);
        } else throw new NotFoundException("Не найдена организация с id = " + id);
    }

    @PostMapping("save")
    public void saveOrg(@RequestBody @Valid OrganizationSaveDto organizationSaveDTO) {
        organizationService.save(organizationSaveDTO);
    }

    @PostMapping("update")
    public void updateOrg(@RequestBody @Valid OrganizationUpdateAndGetDto organizationUpdateAndGetDTO) {
        organizationService.update(organizationUpdateAndGetDTO);
    }
}
