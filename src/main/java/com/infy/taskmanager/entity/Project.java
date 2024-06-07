package com.infy.taskmanager.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import java.time.LocalDateTime;
@Data
@Entity
@Table(name = "Projects")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native",strategy = "native")
    private int projectId;

    @NotEmpty
    private String projectName;

    private LocalDateTime dateOfStart;

    @NotEmpty
    private String noOfTeammates;

    @NotEmpty
    private String active;

    @NotEmpty
    private String status;

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "client_Location_Id", referencedColumnName = "clientLocationId", nullable = true)
    private Client client;
}
