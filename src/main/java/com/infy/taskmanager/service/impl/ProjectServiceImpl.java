package com.infy.taskmanager.service.impl;

import com.infy.taskmanager.entity.Client;
import com.infy.taskmanager.entity.Project;
import com.infy.taskmanager.repository.ClientRepository;
import com.infy.taskmanager.repository.ProjectRepository;
import com.infy.taskmanager.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private ClientRepository clientRepository;

    @Override
    public Project createRecord(Project project) {


        project.setDateOfStart(LocalDateTime.now());
        Project project1 = this.projectRepository.save(project);
        Optional<Client> client = this.clientRepository.findById(project.getClient().getClientLocationId());
        project1.setClient(client.get());
        return project1;
    }

    @Override
    public Project updateRecord(Project project) {
        return this.projectRepository.save(project);
    }

    @Override
    public Project getRecord(String id) {
        return null;
    }

    @Override
    public boolean deleteRecord(int id) {
        this.projectRepository.deleteById(id);
        return true;
    }

    @Override
    public List<Project> getAllRecord() {

        return (List<Project>) this.projectRepository.findAll();
    }

    @Override
    public List<Project> getRecordBySearch(String searchBy, String searchText) {

        if (searchBy.equalsIgnoreCase("ProjectName")) {
            System.out.println("in ProjectName Loop");
            return (List<Project>) this.projectRepository.findByProjectName(searchText);
        } else if (searchBy.equalsIgnoreCase("TeamSize")) {
            System.out.println("in ProjectID Loop");
            return (List<Project>) this.projectRepository.findByNoOfTeammates(searchText);
        } else if (searchBy.equalsIgnoreCase("ProjectID")) {
            return (List<Project>) this.projectRepository.findByProjectName(searchText);
        } else {
            return (List<Project>) this.projectRepository.findByProjectName(searchText);
        }
    }

}
