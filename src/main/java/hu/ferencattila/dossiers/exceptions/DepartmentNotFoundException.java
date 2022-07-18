package hu.ferencattila.dossiers.exceptions;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import java.net.URI;

public class DepartmentNotFoundException extends AbstractThrowableProblem {
    public DepartmentNotFoundException(String department) {
        super(URI.create("dossier/department-not-found"),
                "Department not found",
                Status.BAD_REQUEST,
                String.format("Department not found with name: %s!", department));
    }
}
