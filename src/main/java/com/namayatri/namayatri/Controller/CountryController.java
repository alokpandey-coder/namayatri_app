package com.namayatri.namayatri.Controller;

import com.namayatri.namayatri.Payload.CountryDto;
import com.namayatri.namayatri.Service.CountryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
