package hu.ferencattila.dossiers.controllers;

import hu.ferencattila.dossiers.dtos.CaseManagerDetails;
import hu.ferencattila.dossiers.dtos.CreateCaseManagerCommand;
import hu.ferencattila.dossiers.dtos.UpdateCaseManagerCommand;
import hu.ferencattila.dossiers.service.DossiersCaseManagersService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/api/managers")
public class CaseManagerController {

    private DossiersCaseManagersService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CaseManagerDetails createCaseManager(@Valid @RequestBody CreateCaseManagerCommand command) {
        return service.createCaseManager(command);
    }

    @GetMapping
    public List<CaseManagerDetails> findCaseManagers(@RequestParam(name = "department") Optional<String> departmentSubstring) {
        return service.findCaseManagers(departmentSubstring);
    }

    @PutMapping(value = "/{id}")
    public CaseManagerDetails updateCaseManagerDepartment(@PathVariable(value = "id") long id, @RequestBody UpdateCaseManagerCommand command) {
        return service.updateCaseManagerDepartment(id, command);
    }
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeCaseManager(@PathVariable("id") long id) {
        service.removeCaseManager(id);
    }
}
