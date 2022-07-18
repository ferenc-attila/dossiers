package hu.ferencattila.dossiers.exceptions;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import java.net.URI;

public class CaseManagerNotFoundException extends AbstractThrowableProblem {
    public CaseManagerNotFoundException(long id) {
        super(URI.create("managers/manager-not-found"),
                "Manager not found!",
                Status.NOT_FOUND,
                String.format("Case manager not found with id: %d!", id));
    }
}
