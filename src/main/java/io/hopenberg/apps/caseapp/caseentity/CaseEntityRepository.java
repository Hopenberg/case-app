package io.hopenberg.apps.caseapp.caseentity;

import io.hopenberg.apps.caseapp.caseentity.CaseCountByState;
import io.hopenberg.apps.caseapp.caseentity.CaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface CaseEntityRepository extends JpaRepository<CaseEntity, Integer> {
    @Query(
        "SELECT new io.hopenberg.apps.caseapp.caseentity.CaseCountByState(c.state, COUNT(c.state)) "
        + "FROM CaseEntity c WHERE c.dateOfEntry >= :start AND c.dateOfEntry <= :end AND c.type = :type "
        + "GROUP BY c.state "
    )
    List<CaseCountByState> findByDateOfEntryBetweenAndType(@Param("start") Date start, @Param("end") Date end, @Param("type") String type);

    @Query(
            "SELECT new io.hopenberg.apps.caseapp.caseentity.CaseCountByState(c.state, COUNT(c.state)) "
            + "FROM CaseEntity c WHERE c.type = :type GROUP BY c.state "
    )
    List<CaseCountByState> findByType(String type);
}
