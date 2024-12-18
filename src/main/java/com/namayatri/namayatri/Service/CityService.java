package com.namayatri.namayatri.Service;

import com.namayatri.namayatri.Model.City;
import com.namayatri.namayatri.Payload.CityDto;
import com.namayatri.namayatri.Repository.CityRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

        Optional<City> cityOptional=  cityRepository.findByName(dto.getName());
        if (cityOptional.isPresent()) {

            throw new RuntimeException("City already exists in Database");
        }

       City city = mapToEntity(dto);
       City savedCity =cityRepository.save(city);
       CityDto cityDto =mapToDto(savedCity);
       return cityDto;
    }

    public void deleteCity(int id) {
        cityRepository.deleteById(id);
    }

    public CityDto updateCity(int id, CityDto dto) {

       City city = cityRepository.findById(id).get();
       city.setName(dto.getName());
        cityRepository.save(city);
       CityDto cityDto= mapToDto(city);
       return cityDto;
    }

    public List<CityDto> getAllCity() {

        List<City> city =cityRepository.findAll();
        List<CityDto> cityDto = city.stream().map(e->mapToDto(e)).collect(Collectors.toList());
        return cityDto;
    }
}
