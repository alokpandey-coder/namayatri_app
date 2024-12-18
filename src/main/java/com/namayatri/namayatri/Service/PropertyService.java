package com.namayatri.namayatri.Service;

import com.namayatri.namayatri.Model.Property;
import com.namayatri.namayatri.Model.User;
import com.namayatri.namayatri.Payload.PropertyDto;
import com.namayatri.namayatri.Payload.UserDto;
import com.namayatri.namayatri.Repository.AreaRepository;
import com.namayatri.namayatri.Repository.CityRepository;
import com.namayatri.namayatri.Repository.CountryRepository;
import com.namayatri.namayatri.Repository.PropertyRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PropertyService {

    private final PropertyRepository propertyRepository;
    private final CountryRepository countryRepository;
    private final CityRepository cityRepository;
    private final AreaRepository areaRepository;
    private final ModelMapper modelMapper;

    public PropertyService(PropertyRepository propertyRepository, CountryRepository countryRepository, CityRepository cityRepository, AreaRepository areaRepository, ModelMapper modelMapper) {
        this.propertyRepository = propertyRepository;
        this.countryRepository = countryRepository;
        this.cityRepository = cityRepository;
        this.areaRepository = areaRepository;
        this.modelMapper = modelMapper;
    }

    Property mapToEntity(PropertyDto dto){
        Property property =   modelMapper.map(dto,Property.class);
        //The mapping constrains are not automaticallly done we have to add one by one manually

        property.setCountry(countryRepository.findById(dto.getCountryId())
                           .orElseThrow(()-> new RuntimeException("Country Not Found")));

        property.setCity(cityRepository.findById(dto.getCityId())
                           .orElseThrow(()-> new RuntimeException("City Not Found")));

        property.setArea(areaRepository.findById(dto.getAreaId())
                           .orElseThrow(()-> new RuntimeException("Area Not Found")));





        return property;
    }

    PropertyDto mapToDto(Property property){
        return  modelMapper.map(property,PropertyDto.class);
    }


    public PropertyDto addProperty(PropertyDto dto) {

      Optional<Property> propertyName = propertyRepository.findByName(dto.getName());

      if(propertyName.isPresent()){
          throw new RuntimeException("Property Already Exists");
      }

      Property property =mapToEntity(dto);
      Property savedProperty  =propertyRepository.save(property);
      PropertyDto prop =mapToDto(savedProperty);

      return prop;
    }

    public List<Property> searchProperty(String searchName) {

        List<Property> properties = propertyRepository.searchProperty(searchName);

        return properties; 
    }
}
