package hu.ferencattila.dossiers.service;

import hu.ferencattila.dossiers.dtos.*;
import hu.ferencattila.dossiers.exceptions.CaseManagerNotFoundException;
import hu.ferencattila.dossiers.exceptions.DepartmentNotFoundException;
import hu.ferencattila.dossiers.exceptions.DossierNotFoundException;
import hu.ferencattila.dossiers.exceptions.TooLittleManagersAtDepartmentException;
import hu.ferencattila.dossiers.models.CaseManager;
import hu.ferencattila.dossiers.models.Dossier;
import hu.ferencattila.dossiers.models.DossierStatus;
import hu.ferencattila.dossiers.repositories.CaseManagerRepository;
import hu.ferencattila.dossiers.repositories.DossierRepository;
import lombok.AllArgsConstructor;
import org.apache.catalina.Manager;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
@Transactional
public class DossiersCaseManagersService {

    private DossierRepository dossierRepository;

    private CaseManagerRepository caseManagerRepository;

    private ModelMapper mapper;

    public CaseManagerDetails createCaseManager(CreateCaseManagerCommand command) {
        CaseManager manager = new CaseManager(command.getName(), command.getDepartment());
        caseManagerRepository.save(manager);
        return mapper.map(manager, CaseManagerDetails.class);
    }

    public List<CaseManagerDetails> findCaseManagers(Optional<String> departmentSubstring) {
        return caseManagerRepository.findCaseManagerByDepartmentName(departmentSubstring).stream()
                .map(manager -> mapper.map(manager, CaseManagerDetails.class))
                .toList();
    }

    public CaseManagerDetails updateCaseManagerDepartment(long id, UpdateCaseManagerCommand command) {
        CaseManager manager = findCaseManagerById(id);
        manager.setDepartment(command.getDepartment());
        return mapper.map(manager, CaseManagerDetails.class);
    }

    public DossierDetails createDossier(CreateDossierCommand command) {
        validateDepartment(command.getDepartment());
        Dossier dossier = new Dossier(command.getDateOfArrival(),
                command.getDeadline(),
                command.getSubject(),
                command.getDescription(),
                command.getDepartment());
        dossier.setStatus(DossierStatus.UNANSWERED);
        CaseManager manager = caseManagerRepository.findCaseManagerWithTheLeastDossiers(dossier.getDepartment()).get(0);
        dossierRepository.save(dossier);
        manager.addDossier(dossier);
        return mapper.map(dossier, DossierDetails.class);
    }

    public List<DossierDetails> findUnansweredDossiers(Optional<String> departmentSubstring) {
        List<CaseManager> managers = caseManagerRepository.findCaseManagerByDepartmentName(departmentSubstring);
        return managers.stream()
                .flatMap(caseManager -> caseManager.getDossiers().stream())
                .map(dossier -> mapper.map(dossier, DossierDetails.class))
                .toList();
    }

    public DossierDetails answerDossier(long id) {
        Dossier dossier = findDossierById(id);
        dossier.setStatus(DossierStatus.SOLVED);
        dossier.setCaseManager(null);
        return mapper.map(dossier, DossierDetails.class);
    }

    public void removeCaseManager(long id) {
        CaseManager manager = findCaseManagerById(id);
        checkIfDepartmentHasAtLeastTwoManagers(manager.getDepartment());
        List<Dossier> dossiers = manager.getDossiers();
        dossiers.forEach(dossier -> dossier.setCaseManager(null));
        caseManagerRepository.delete(manager);
        dossiers.forEach(dossier -> dossier.setCaseManager(caseManagerRepository.findCaseManagerWithTheLeastDossiers(dossier.getDepartment()).get(0)));
    }

    private void checkIfDepartmentHasAtLeastTwoManagers(String department) {
        if (caseManagerRepository.findCaseManagerByDepartmentName(Optional.of(department)).size() < 2) {
            throw new TooLittleManagersAtDepartmentException(department);
        }
    }

    private Dossier findDossierById(long id) {
        return dossierRepository.findById(id)
                .orElseThrow(() -> new DossierNotFoundException(id));
    }

    private void validateDepartment(String department) {
        if (caseManagerRepository.findAll().stream().noneMatch(manager -> department.equals(manager.getDepartment()))) {
            throw new DepartmentNotFoundException(department);
        }
    }

    private CaseManager findCaseManagerById(long id) {
        return caseManagerRepository.findById(id)
                .orElseThrow(() -> new CaseManagerNotFoundException(id));
    }
}
