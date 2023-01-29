package io.hopenberg.apps.caseapp.party;

import io.hopenberg.apps.caseapp.party.Party;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PartyRepository extends JpaRepository<Party, Integer> {

    @Query("SELECT p FROM Party p "
            + "LEFT JOIN p.partyAddresses pa "
            + "LEFT JOIN p.caseEntity ce "
            + "WHERE ce.state = :caseState"
    )
    List<Party> findAllActiveSitesByCaseState(String caseState);

    List<Party> findAllByActiveTrueAndCaseEntity_state(String caseState);
}
