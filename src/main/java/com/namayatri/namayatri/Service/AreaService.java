package com.namayatri.namayatri.Service;

import com.namayatri.namayatri.Model.Area;
import com.namayatri.namayatri.Payload.AreaDto;
import com.namayatri.namayatri.Repository.AreaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

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

       Area areaEntity = mapToEntity(area);
       Area savedArea =areaRepository.save(areaEntity);
       AreaDto areaDto =mapToDto(savedArea);
       return areaDto;
    }
}
