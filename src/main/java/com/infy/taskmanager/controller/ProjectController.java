package com.infy.taskmanager.controller;

import com.infy.taskmanager.constant.TaskConstants;
import com.infy.taskmanager.dto.ProjectDto;
import com.infy.taskmanager.dto.ResponseDto;
import com.infy.taskmanager.entity.Project;
import com.infy.taskmanager.service.ProjectService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = "/task", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @PostMapping("/createProject")
    public ResponseEntity<Project> createProject(@Valid @RequestBody  Project project)
    {
        Project project1 = this.projectService.createRecord(project);
       return ResponseEntity.status(HttpStatus.CREATED)
               .body(project1);
    }

    @PutMapping("/updateProject")
    public ResponseEntity<Project> updateProject(@Valid @RequestBody  Project project)
    {
        Project project1 = this.projectService.updateRecord(project);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(project1);
    }
    @GetMapping("/getAllProjects")
    public ResponseEntity<List<Project>> fetchAllUserDetails() {
        List<Project> projectDetails = projectService.getAllRecord();
        return ResponseEntity.status(HttpStatus.OK).body(projectDetails);
    }
    @DeleteMapping("/deleteProjects")
    public ResponseEntity<ProjectDto> fetchAllUserDetails(@RequestParam int projectId ) {
        boolean isDeleted= projectService.deleteRecord(projectId);
        return ResponseEntity.status(HttpStatus.OK).body(new ProjectDto("Record deleted suscessfulyy"));
    }

    @GetMapping("/getProjectsBy/{searchBy}/{searchText}")
    public ResponseEntity<List<Project>> fetchAllUserDetails(@PathVariable String searchBy, @PathVariable String searchText) {
        List<Project> projectDetails = projectService.getRecordBySearch(searchBy,searchText);
        return ResponseEntity.status(HttpStatus.OK).body(projectDetails);
    }
}
