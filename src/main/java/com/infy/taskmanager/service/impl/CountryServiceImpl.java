package com.infy.taskmanager.service.impl;


import com.infy.taskmanager.constant.TaskConstants;
import com.infy.taskmanager.entity.Country;
import com.infy.taskmanager.exception.CustomerAlreadyExistsException;
import com.infy.taskmanager.exception.ResourceNotFoundException;
import com.infy.taskmanager.repository.CountryRepository;
import com.infy.taskmanager.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {
    @Autowired
    private CountryRepository countryRepository;

    @Override
    public void createRecord(Country country) {
        String cName=country.getCountryName().trim();
        Country country1 = countryRepository.findByCountryName(cName);
        if(country1!=null){
            throw new CustomerAlreadyExistsException("Country Name Already Exist");
        }
        country.setCountryName(cName);
        country.setStatus(TaskConstants.OPEN);
        countryRepository.save(country);
    }
    @Override
    public boolean updateRecord(Country country) {
        return false;
    }

    @Override
    public Country getRecord(String id) {
        Country country = countryRepository.findById(Integer.parseInt(id)).orElseThrow(
                () -> {
                    return new ResourceNotFoundException("Country", "CountryID", id);
                }
        );
        return country;
    }

    @Override
    public boolean deleteRecord(Country country) {
        return false;
    }

    @Override
    public List<Country> getAllRecord() {
        List<Country> countries= (List<Country>) countryRepository.findAll();
        return countries;
    }
    @Override
    public Page<Country> findCountries(int pageNum, String sortField, String sortDir){
        int pageSize = 5;
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize,
                sortDir.equals("asc") ? Sort.by(sortField).ascending()
                        : Sort.by(sortField).descending());
        Page<Country> msgPage = countryRepository.findByStatus(TaskConstants.OPEN,pageable);
        return msgPage;
    }
}
