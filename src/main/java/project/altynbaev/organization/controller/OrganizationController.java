package project.altynbaev.organization.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.altynbaev.exception.BadRequestException;
import project.altynbaev.organization.service.OrganizationService;
import project.altynbaev.organization.view.*;
import project.altynbaev.view.ResultView;

import javax.validation.Valid;
import java.util.List;

@Api(value = "OrganizationController")
@RestController
@RequestMapping("/api/organization")
public class OrganizationController {
    private final OrganizationService organizationService;

    @Autowired
    public OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    @ApiOperation(value = "Получить список организаций по фильтру", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = OrganizationListRespView.class)
    })
    @PostMapping("/list")
    public List<OrganizationListRespView> getList(@Valid @RequestBody OrganizationListReqView organizationListReqView, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.toString());
        }
        else {
            return organizationService.getOrganizationByFilter(organizationListReqView);
        }
    }

    @ApiOperation(value = "Получить организацию по id", httpMethod = "GET")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = OrganizationByIdRespView.class)
    })
    @GetMapping("/{id:[\\d]+}")
    public OrganizationByIdRespView getById(@PathVariable Long id) {
        return organizationService.getOrganizationById(id);
    }

    @ApiOperation(value = "Добавить организацию", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = ResultView.class)
    })
    @PostMapping("/save")
    public void save(@Valid @RequestBody OrganizationSaveReqView organizationSaveReqView, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.toString());
        }
        else {
            organizationService.saveOrganization(organizationSaveReqView);
        }
    }

    @ApiOperation(value = "Обновить организацию", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = ResultView.class)
    })
    @PostMapping("/update")
    public void update(@Valid @RequestBody OrganizationUpdateReqView organizationUpdateReqView, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.toString());
        }
        else {
            organizationService.updateOrganization(organizationUpdateReqView);
        }
    }
}
