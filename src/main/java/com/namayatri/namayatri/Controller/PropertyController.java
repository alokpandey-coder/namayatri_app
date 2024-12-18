package com.namayatri.namayatri.Controller;

import com.namayatri.namayatri.Payload.PropertyDto;
import com.namayatri.namayatri.Service.PropertyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/property")
public class PropertyController {

    private final PropertyService propertyService;

    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    //http://localhost:8080/api/property/newProperty

    @PostMapping("/newProperty")
    public ResponseEntity<PropertyDto> addNewProperty(@RequestBody PropertyDto dto){

       PropertyDto property =  propertyService.addProperty(dto);

       return new ResponseEntity<>(property, HttpStatus.CREATED);

    }
}
