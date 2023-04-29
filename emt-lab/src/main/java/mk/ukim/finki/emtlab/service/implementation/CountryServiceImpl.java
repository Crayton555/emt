package mk.ukim.finki.emtlab.service.implementation;

import mk.ukim.finki.emtlab.model.Country;
import mk.ukim.finki.emtlab.repository.CountryRepository;
import mk.ukim.finki.emtlab.service.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryServiceImpl implements CountryService {
    private final CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public Optional<Country> findById(Long id) {
        return this.countryRepository.findById(id);
    }

    @Override
    public Optional<Country> create(String name, String continent) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException();
        }
        Country c = new Country(name, continent);
        countryRepository.save(c);
        return Optional.of(c);
    }

    @Override
    public Country update(String name, String continent) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException();
        }
        Country c = new Country(name, continent);
        countryRepository.save(c);
        return c;
    }

    @Override
    public void deleteById(Long id) {
        this.countryRepository.deleteById(id);
    }

    @Override
    public List<Country> listCountries() {
        return countryRepository.findAll();
    }

    @Override
    public List<Country> searchCountries(String searchText) {
        return countryRepository.findAllByNameLike(searchText);
    }
}
