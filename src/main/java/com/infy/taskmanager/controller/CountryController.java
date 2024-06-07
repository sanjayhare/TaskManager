package com.infy.taskmanager.controller;


import com.infy.taskmanager.constant.TaskConstants;
import com.infy.taskmanager.dto.ResponseDto;
import com.infy.taskmanager.entity.Country;
import com.infy.taskmanager.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping(path = "/task", produces = MediaType.APPLICATION_JSON_VALUE)
public class CountryController {

    @Autowired
    private CountryService countryService;

    @PostMapping("/createCountry")
    public ResponseEntity<ResponseDto> createUser(@RequestBody Country country) {
        countryService.createRecord(country);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDto(TaskConstants.STATUS_201, TaskConstants.MESSAGE_201));
    }

   /* @GetMapping("/getCountries")
    public ResponseEntity<List<Country>> getAllCountries() {
        List <Country> countries = (List<Country>) countryService.getAllRecord();
        return ResponseEntity.status(HttpStatus.OK).body(countries);
    }*/
    @GetMapping("/getCountries/page/{pageNum}")
    public ResponseEntity<Page<Country>> getAllCountries(
            @PathVariable(name = "pageNum") int pageNum, @RequestParam("sortField") String sortField,
                                                           @RequestParam("sortDir") String sortDir) {
        Page<Country> countries = countryService.findCountries(pageNum,sortField,sortDir);
        return ResponseEntity.status(HttpStatus.OK).body(countries);
    }

}
