package com.devops.tracker.model;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Build {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String status; // SUCCESS, FAILED
    private LocalDateTime timestamp;
    private Long duration; // in seconds
    private String failureReason;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    @OneToOne(mappedBy = "build", cascade = CascadeType.ALL)
    private Deployment deployment;
}
