package hu.ferencattila.dossiers.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "dossiers")
public class Dossier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "arrival_date", nullable = false)
    private LocalDate dateOfArrival;

    @Column(nullable = false)
    private int deadline;

    @Column(nullable = false)
    private String subject;

    private String description;

    @Column(nullable = false, length = 150)
    private String department;

    @Enumerated(value = EnumType.STRING)
    private DossierStatus status;

    @ManyToOne
    @JoinColumn(name = "case_manager_id")
    private CaseManager caseManager;
}