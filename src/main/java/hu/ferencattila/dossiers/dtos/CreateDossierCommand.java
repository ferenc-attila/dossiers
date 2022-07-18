package hu.ferencattila.dossiers.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CreateDossierCommand {

    @NotNull(message = "Date of dossier's arrive can not be null!")
    @PastOrPresent(message = "Date of dossier's arrive can not be in the future!")
    private LocalDate dateOfArrival;

    @Positive(message = "Deadline can not be a negative number or zero!")
    private int deadline;

    @NotBlank(message = "Subject can not be blank!")
    private String subject;

    private String description;

    @NotBlank(message = "Department can not be empty!")
    @Size(min = 5, max = 150, message = "Department must consist of at least 5 and maximum of 150 characters!")
    private String department;
}
