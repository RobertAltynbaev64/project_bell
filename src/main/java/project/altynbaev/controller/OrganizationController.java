package project.altynbaev.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import project.altynbaev.dto.organization.OrganizationFilterInDto;
import project.altynbaev.dto.organization.OrganizationFilterOutDto;
import project.altynbaev.dto.organization.OrganizationSaveDto;
import project.altynbaev.dto.organization.OrganizationUpdateAndGetDto;
import project.altynbaev.service.organization.OrganizationService;

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
    public List<OrganizationFilterOutDto> filter(@RequestBody OrganizationFilterInDto organizationFilterInDTO) {
        return organizationService.filter(organizationFilterInDTO);
    }

    @GetMapping("{id}")
    public OrganizationUpdateAndGetDto getOrg(@PathVariable int id) {
        return organizationService.findById(id);
    }

    @PostMapping("save")
    public void saveOrg(@RequestBody OrganizationSaveDto organizationSaveDTO) {
        organizationService.save(organizationSaveDTO);
    }

    @PostMapping("update")
    public void updateOrg(@RequestBody OrganizationUpdateAndGetDto organizationUpdateAndGetDTO) {
        organizationService.update(organizationUpdateAndGetDTO);
    }
}
