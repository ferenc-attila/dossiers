package hu.ferencattila.dossiers.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CaseManagerDetails {

    private Long id;

    private String name;

    private String department;

    private List<DossierDetails> dossiers = new ArrayList<>();
}
