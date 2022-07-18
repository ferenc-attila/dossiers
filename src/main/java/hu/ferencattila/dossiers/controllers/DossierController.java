package hu.ferencattila.dossiers.controllers;

import hu.ferencattila.dossiers.dtos.CreateDossierCommand;
import hu.ferencattila.dossiers.dtos.DossierDetails;
import hu.ferencattila.dossiers.service.DossiersCaseManagersService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/api/dossiers")
public class DossierController {

    private DossiersCaseManagersService service;

    @PostMapping
    public DossierDetails createDossier(@Valid @RequestBody CreateDossierCommand command) {
        return service.createDossier(command);
    }

    @GetMapping
    public List<DossierDetails> findUnansweredDossiers(@RequestParam(value = "department") Optional<String> departmentSubString) {
        return service.findUnansweredDossiers(departmentSubString);
    }

    @PutMapping(value = "/{id}")
    public DossierDetails answerDossier(@PathVariable(value = "id") long id) {
        return service.answerDossier(id);
    }
}
