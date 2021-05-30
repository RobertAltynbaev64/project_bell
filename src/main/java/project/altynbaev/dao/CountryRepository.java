package project.altynbaev.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.altynbaev.model.Country;

import java.util.Optional;

@Repository
public interface CountryRepository extends JpaRepository<Country, Integer> {

    Optional<Country> findById(int id);

    Optional<Country> findByCountryCode(int countryCode);
}
