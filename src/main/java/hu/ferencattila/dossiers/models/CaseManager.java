package hu.ferencattila.dossiers.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "case_managers")
public class CaseManager {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 150)
    private String department;

    @OneToMany(mappedBy = "caseManager")
    private List<Dossier> dossiers = new ArrayList<>();

    public CaseManager(String name, String department) {
        this.name = name;
        this.department = department;
    }

    public void addDossier(Dossier dossier) {
        dossiers.add(dossier);
        dossier.setCaseManager(this);
    }
}
