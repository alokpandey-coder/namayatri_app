package com.namayatri.namayatri.Controller;


import com.namayatri.namayatri.Payload.CityDto;
import com.namayatri.namayatri.Service.CityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
