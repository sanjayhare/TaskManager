package com.infy.taskmanager.service;

import com.infy.taskmanager.entity.Project;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface ProjectService {

    public Project createRecord(Project project);
    public Project updateRecord(Project project);
    public Project getRecord(String id);
    public boolean deleteRecord(int id);
    public List<Project> getAllRecord();
    public List<Project> getRecordBySearch(String searchBy,String searchText);

}
