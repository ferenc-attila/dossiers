package hu.ferencattila.dossiers.repositories;

import hu.ferencattila.dossiers.models.Dossier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DossierRepository extends JpaRepository<Dossier, Long> {
}
