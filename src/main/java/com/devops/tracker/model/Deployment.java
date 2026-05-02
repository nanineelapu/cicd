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
public class Deployment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String status; // DEPLOYED, FAILED, ROLLED_BACK
    private LocalDateTime timestamp;

    @OneToOne
    @JoinColumn(name = "build_id")
    private Build build;
}
