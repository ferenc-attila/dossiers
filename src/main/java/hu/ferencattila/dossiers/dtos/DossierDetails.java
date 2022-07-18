package hu.ferencattila.dossiers.dtos;

import hu.ferencattila.dossiers.models.DossierStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DossierDetails {

    private Long id;

    private LocalDate dateOfArrival;

    private int deadline;

    private String subject;

    private String description;

    private String department;

    private DossierStatus status;
}
