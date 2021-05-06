package project.altynbaev.dictionary.country.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.altynbaev.dictionary.country.dao.CountryDao;
import project.altynbaev.dictionary.country.model.Country;
import project.altynbaev.dictionary.country.view.CountryView;

import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {

    private CountryDao dao;

    @Autowired
    public CountryServiceImpl(CountryDao dao) {
        this.dao = dao;
    }

    @Override
    @Transactional(readOnly = true)
    public Country getCountryByCode(Integer countryCode) {
        return dao.getCountryByCode(countryCode);
    }

    @Override
    @Transactional(readOnly = true)
    public Country getCountryByName(String countryName) {
        return dao.getCountryByName(countryName);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CountryView> getList() {
        return dao.getList();
    }
}
