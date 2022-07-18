package hu.ferencattila.dossiers.exceptions;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import java.net.URI;

public class TooLittleManagersAtDepartmentException extends AbstractThrowableProblem {
    public TooLittleManagersAtDepartmentException(String department) {
        super(URI.create("managers/too-little-managers"),
                "Too little managers",
                Status.BAD_REQUEST,
                String.format("Too little managers at department: %s", department));
    }
}
