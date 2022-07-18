package hu.ferencattila.dossiers.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CreateCaseManagerCommand {

    @NotBlank(message = "Name can not be empty!")
    @Size(min = 5, max = 100, message = "Name must consist of at least 5 and maximum of 100 characters!")
    private String name;

    @NotBlank(message = "Department can not be empty!")
    @Size(min = 5, max = 150, message = "Department must consist of at least 5 and maximum of 150 characters!")
    private String department;
}
