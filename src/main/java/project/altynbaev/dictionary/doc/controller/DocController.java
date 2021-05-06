package project.altynbaev.dictionary.doc.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.altynbaev.dictionary.doc.service.DocService;
import project.altynbaev.dictionary.doc.view.DocView;

import java.util.List;

@Api(value = "DocController")
@RestController
@RequestMapping("/api/docs")
public class DocController {
    private final DocService docService;

    @Autowired
    public DocController(DocService docService) {
        this.docService = docService;
    }

    @ApiOperation(value = "Получить список документов из справочника", httpMethod = "GET")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = DocView.class)
    })
    @GetMapping()
    public List<DocView> getList() {
        return docService.getList();
    }
}
