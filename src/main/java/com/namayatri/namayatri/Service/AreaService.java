package com.namayatri.namayatri.Service;

import com.namayatri.namayatri.Model.Area;
import com.namayatri.namayatri.Payload.AreaDto;
import com.namayatri.namayatri.Repository.AreaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AreaService {

    private final AreaRepository areaRepository;
    private final ModelMapper modelMapper;

    public AreaService(AreaRepository areaRepository, ModelMapper modelMapper) {
        this.areaRepository = areaRepository;
        this.modelMapper = modelMapper;
    }

    Area mapToEntity(AreaDto dto){
        return modelMapper.map(dto, Area.class);
    }

    AreaDto mapToDto(Area area){
        return modelMapper.map(area,AreaDto.class);
    }

    public AreaDto addArea(AreaDto area) {

        Optional<Area> areaOptional = areaRepository.findByName(area.getName());
        if(areaOptional.isPresent()){
            throw new RuntimeException("Area " + area.getName() + " already exists");
        }

       Area areaEntity = mapToEntity(area);
       Area savedArea =areaRepository.save(areaEntity);
       AreaDto areaDto =mapToDto(savedArea);
       return areaDto;
    }

    public void deleteArea(int id) {
        areaRepository.deleteById(id);
    }

    public AreaDto updateArea(int id, AreaDto dto) {
        Area area =areaRepository.findById(id).get();
        area.setName(dto.getName());
        areaRepository.save(area);
        AreaDto areaDto=mapToDto(area);
        return areaDto;
    }

    public List<AreaDto> getAllArea() {

        List<Area> area = areaRepository.findAll();
        List<AreaDto> areaDtos = area.stream().map(e->mapToDto(e)).collect(Collectors.toList());
        return areaDtos;
    }
}
