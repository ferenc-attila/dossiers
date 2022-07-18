package hu.ferencattila.dossiers.exceptions;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import java.net.URI;

public class DossierNotFoundException extends AbstractThrowableProblem {
    public DossierNotFoundException(long id) {
        super(URI.create("dossier/not-found"),
                "Dossier not found",
                Status.NOT_FOUND,
                String.format("Dossier not found with id: %d", id));
    }
}
