package project.altynbaev.dictionary.country.dao;

import project.altynbaev.dictionary.country.model.Country;
import project.altynbaev.dictionary.country.view.CountryView;

import java.util.List;

public interface CountryDao {

    public Country getCountryByCode(Integer countryCode);

    public Country getCountryByName(String countryName);

    public List<CountryView> getList();
}
