package project.altynbaev.office.controller;

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
import project.altynbaev.office.service.OfficeService;
import project.altynbaev.office.view.OfficeByIdRespView;
import project.altynbaev.office.view.OfficeListReqView;
import project.altynbaev.office.view.OfficeListRespView;
import project.altynbaev.office.view.OfficeSaveReqView;
import project.altynbaev.office.view.OfficeUpdateReqView;
import project.altynbaev.view.ResultView;

import javax.validation.Valid;
import java.util.List;

@Api(value = "OfficeController")
@RestController
@RequestMapping("/api/office")
public class OfficeController {
    private final OfficeService officeService;

    @Autowired
    public OfficeController(OfficeService officeService) {
        this.officeService = officeService;
    }

    @ApiOperation(value = "Получить список офисов организации по фильтру", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = OfficeListRespView.class)
    })
    @PostMapping("/list")
    public List<OfficeListRespView> getList(@Valid @RequestBody OfficeListReqView officeListReqView, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.toString());
        } else {
            return officeService.getOfficeByFilter(officeListReqView);
        }
    }

    @ApiOperation(value = "Получить офис организации по id", httpMethod = "GET")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = OfficeByIdRespView.class)
    })
    @GetMapping("/{id:[\\d]+}")
    public OfficeByIdRespView getById(@PathVariable Long id) {
        return officeService.getOfficeById(id);
    }

    @ApiOperation(value = "Добавить офис", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = ResultView.class)
    })
    @PostMapping("/save")
    public void save(@Valid @RequestBody OfficeSaveReqView officeSaveReqView, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.toString());
        } else {
            officeService.saveOffice(officeSaveReqView);
        }
    }

    @ApiOperation(value = "Обновить офис", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = ResultView.class)
    })
    @PostMapping("/update")
    public void update(@Valid @RequestBody OfficeUpdateReqView officeUpdateReqView, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.toString());
        } else {
            officeService.updateOffice(officeUpdateReqView);
        }
    }
}
