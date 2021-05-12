package project.altynbaev.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.altynbaev.model.Country;
import project.altynbaev.service.country.CountryService;

import java.util.List;

@RestController
@RequestMapping("countries")
public class CountryController {

    private final CountryService countryService;

    @Autowired
    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping
    public List<Country> list(){
        return countryService.getAll();
    }
}
