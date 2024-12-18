package com.namayatri.namayatri.Controller;

import com.namayatri.namayatri.Payload.AreaDto;
import com.namayatri.namayatri.Payload.CityDto;
import com.namayatri.namayatri.Service.AreaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class AreaController {

    private final AreaService areaService;

    public AreaController(AreaService areaService) {
        this.areaService = areaService;
    }

    //http://localhost:8080/api/v1/addArea
    @PostMapping("/addArea")
    public ResponseEntity<AreaDto> addCity(@RequestBody AreaDto area){

        AreaDto areaDto=areaService.addArea(area);

        return new ResponseEntity<>(areaDto, HttpStatus.CREATED);
    }

    //http://localhost:8080/api/v1/deleteArea?id=2
    @DeleteMapping("/deleteArea")
    public ResponseEntity<String> deleteArea(@RequestParam int id){
        areaService.deleteArea(id);
        return new ResponseEntity<>("Area are Removed from Record",HttpStatus.OK);
    }

    //http://localhost:8080/api/v1/updateArea?id=2
    @PutMapping("/updateArea")
    public ResponseEntity<AreaDto> updateArea(@RequestParam int id, @RequestBody AreaDto dto){
        AreaDto areaDto =areaService.updateArea(id,dto);
        return new ResponseEntity<>(areaDto,HttpStatus.OK);
    }

    //http://localhost:8080/api/v1/ListOfArea
    @GetMapping("/ListOfArea")
    public ResponseEntity<List<AreaDto>> getAllArea(){
        List<AreaDto> area = areaService.getAllArea();
        return new ResponseEntity<>(area,HttpStatus.OK);
    }
}
