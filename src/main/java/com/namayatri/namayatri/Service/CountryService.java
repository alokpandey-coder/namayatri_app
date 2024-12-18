package com.namayatri.namayatri.Service;

import com.namayatri.namayatri.Model.Country;
import com.namayatri.namayatri.Payload.CountryDto;
import com.namayatri.namayatri.Repository.CountryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CountryService {

    private final CountryRepository countryRepository;
    private final ModelMapper modelMapper;

    public CountryService(CountryRepository countryRepository, ModelMapper modelMapper) {
        this.countryRepository = countryRepository;
        this.modelMapper = modelMapper;
    }

    Country mapToEntity(CountryDto dto){
        return modelMapper.map(dto,Country.class);

    }

   CountryDto mapToDto(Country country){

        return modelMapper.map(country,CountryDto.class);
    }

    public CountryDto addCountry(CountryDto dto) {

        Optional<Country> countryOptional = countryRepository.findByName(dto.getName());

        if(countryOptional.isPresent()){
            throw new RuntimeException("Country is already Present in database");
        }


       Country country = mapToEntity(dto);
       Country savedCountry =countryRepository.save(country);
       CountryDto countryDto = mapToDto(savedCountry);
       return countryDto;
    }

    public void deleteCountry(int id) {
        countryRepository.deleteById(id);
    }

    public CountryDto updateCountry(int id, CountryDto dto) {

        Country countryRecord = countryRepository.findById(id).get();

        countryRecord.setName(dto.getName());
        countryRepository.save(countryRecord);

        CountryDto countryDto = mapToDto(countryRecord);
        return countryDto;
    }

    public List<CountryDto> getAllCountry() {
        List<Country> allCountry = countryRepository.findAll();
        List<CountryDto> allCountryDto =allCountry.stream()
                                                      .map(e->mapToDto(e))
                                                      .collect(Collectors.toList());

        return allCountryDto;
    }
}
