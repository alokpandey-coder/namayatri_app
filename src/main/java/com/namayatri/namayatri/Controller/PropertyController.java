package com.namayatri.namayatri.Controller;

import com.namayatri.namayatri.Model.Property;
import com.namayatri.namayatri.Payload.PropertyDto;
import com.namayatri.namayatri.Service.PropertyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    //http://localhost:8080/api/property/{searchName}
    @GetMapping("/{searchName}")
    public ResponseEntity<List<Property>> searchProperty(@PathVariable String searchName){

        List<Property> list = propertyService.searchProperty(searchName);

        return new ResponseEntity<>(list,HttpStatus.OK);
    }
}
