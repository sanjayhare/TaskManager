package com.infy.taskmanager.repository;


import com.infy.taskmanager.entity.Country;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends CrudRepository<Country,Integer> {

    Page<Country> findByStatus(String status, Pageable pageable);
     Country findByCountryName(String cName);
}
