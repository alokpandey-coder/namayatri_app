package com.namayatri.namayatri.Controller;


import com.namayatri.namayatri.Payload.CityDto;
import com.namayatri.namayatri.Service.CityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class CityController {

    private final CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    //http://localhost:8080/api/v1/addcity

    @PostMapping("/addcity")
    public ResponseEntity<CityDto> addCity(@RequestBody CityDto dto){

        CityDto city =cityService.addCity(dto);

        return new ResponseEntity<>(city, HttpStatus.CREATED);
    }

    //http://localhost:8080/api/v1/deleteCity?id=2
    @DeleteMapping("/deleteCity")
    public ResponseEntity<String> deleteCity(@RequestParam int id){
        cityService.deleteCity(id);
        return new ResponseEntity<>("City are Removed from Record",HttpStatus.OK);
    }

    //http://localhost:8080/api/v1/updateCity?id=2
    @PutMapping("/updateCity")
    public ResponseEntity<CityDto> updateCity(@RequestParam int id, @RequestBody CityDto dto){
        CityDto cityDto =cityService.updateCity(id,dto);
        return new ResponseEntity<>(cityDto,HttpStatus.OK);
    }

    //http://localhost:8080/api/v1/ListOfCity
    @GetMapping("/ListOfCity")
    public ResponseEntity<List<CityDto>> getAllCity(){
       List<CityDto> cityDto = cityService.getAllCity();
       return new ResponseEntity<>(cityDto,HttpStatus.OK);
    }
}
