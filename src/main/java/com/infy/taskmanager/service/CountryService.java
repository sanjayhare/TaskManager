package com.infy.taskmanager.service;

;

import com.infy.taskmanager.entity.Country;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CountryService {

   public void createRecord(Country country);
    public boolean updateRecord(Country country);
    public Country getRecord(String id);
    public boolean deleteRecord(Country country);
    public List<Country> getAllRecord();
    public Page<Country> findCountries(int pageNum, String sortField, String sortDir);

}
