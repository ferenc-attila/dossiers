package hu.ferencattila.dossiers.repositories;

import hu.ferencattila.dossiers.models.CaseManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CaseManagerRepository extends JpaRepository<CaseManager, Long> {

    @Query("select c from CaseManager c where (:departmentSubstring is null or upper(c.department) like concat('%', upper(:departmentSubstring), '%'))")
    List<CaseManager> findCaseManagerByDepartmentName(@Param("departmentSubstring") Optional<String> departmentSubstring);

    @Query("select c from CaseManager c where c.department = :department and c.dossiers.size = (select min(c2.dossiers.size) from CaseManager c2)")
    List<CaseManager> findCaseManagerWithTheLeastDossiers(@Param("department") String department);
}
