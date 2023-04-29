package mk.ukim.finki.emtlab.service;

import mk.ukim.finki.emtlab.model.Country;

import java.util.List;
import java.util.Optional;

public interface CountryService {

    Optional<Country> findById(Long id);
    Optional<Country> create(String name, String continent);

    Country update(String name, String continent);

    void deleteById(Long id);

    List<Country> listCountries();

    List<Country> searchCountries(String searchText);
}
