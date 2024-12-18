package com.namayatri.namayatri.Service;

import com.namayatri.namayatri.Model.City;
import com.namayatri.namayatri.Payload.CityDto;
import com.namayatri.namayatri.Repository.CityRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class CityService {

    private final CityRepository cityRepository;
    private final ModelMapper modelMapper;

    public CityService(CityRepository cityRepository, ModelMapper modelMapper) {
        this.cityRepository = cityRepository;
        this.modelMapper = modelMapper;
    }

   City mapToEntity(CityDto dto){
        return modelMapper.map(dto,City.class);
    }

   CityDto mapToDto(City city){
        return modelMapper.map(city,CityDto.class);
    }

    public CityDto addCity(CityDto dto) {

       City city = mapToEntity(dto);
       City savedCity =cityRepository.save(city);
       CityDto cityDto =mapToDto(savedCity);
       return cityDto;
    }
}
