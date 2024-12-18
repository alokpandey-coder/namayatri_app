package com.namayatri.namayatri.Controller;

import com.namayatri.namayatri.Payload.CountryDto;
import com.namayatri.namayatri.Service.CountryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class CountryController {

    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }
    //URL: http://localhost:8080/api/v1/addCountry
    @PostMapping("/addCountry")
    public ResponseEntity<CountryDto> addCountry(@RequestBody CountryDto Dto) {

        CountryDto country = countryService.addCountry(Dto);

        return new ResponseEntity<>(country, HttpStatus.OK);

    }

    //URL: http://localhost:8080/api/v1/deleteCountry?id=2
    @DeleteMapping("/deleteCountry")
    public ResponseEntity<String> deleteCountry(@RequestParam int id){
        countryService.deleteCountry(id);
        return new ResponseEntity<>("Country is Deleted from Database",HttpStatus.OK);
    }

    //URL: http://localhost:8080/api/v1/updateCountry?id=2
    @PutMapping("/updateCountry")
    public ResponseEntity<CountryDto> updateCountry(@RequestParam int id, @RequestBody CountryDto dto){

        CountryDto updatedRecord =countryService.updateCountry(id,dto);

        return new ResponseEntity<>(updatedRecord,HttpStatus.OK);
    }

    //URL: http://localhost:8080/api/v1/ListOfCountry
    @GetMapping("/ListOfCountry")
    public ResponseEntity<List<CountryDto>> getAllCountry(){
         List<CountryDto> listOfCountry =countryService.getAllCountry();

         return new ResponseEntity<>(listOfCountry,HttpStatus.OK);
    }


}
