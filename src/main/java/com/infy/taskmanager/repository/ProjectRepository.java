package com.infy.taskmanager.repository;

import com.infy.taskmanager.entity.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {

    @Query("SELECT p FROM Project p WHERE p.projectName like %:projectName%")
    List<Project> findByProjectName(@Param("projectName") String projectName);

    @Query("SELECT p FROM Project p WHERE p.noOfTeammates like %:size%")
    List<Project> findByNoOfTeammates(@Param("size") String size);
}
