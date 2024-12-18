package com.namayatri.namayatri.Controller;

import com.namayatri.namayatri.Payload.AreaDto;
import com.namayatri.namayatri.Service.AreaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class AreaController {

    private final AreaService areaService;

    public AreaController(AreaService areaService) {
        this.areaService = areaService;
    }

    @PostMapping("/addArea")
    public ResponseEntity<AreaDto> addCity(@RequestBody AreaDto area){

        AreaDto areaDto=areaService.addArea(area);

        return new ResponseEntity<>(areaDto, HttpStatus.CREATED);
    }
}
