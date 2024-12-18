package com.namayatri.namayatri.Service;

import com.namayatri.namayatri.Model.Country;
import com.namayatri.namayatri.Payload.CountryDto;
import com.namayatri.namayatri.Repository.CountryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

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
       Country country = mapToEntity(dto);
       Country savedCountry =countryRepository.save(country);
       CountryDto countryDto = mapToDto(savedCountry);
       return countryDto;
    }
}
