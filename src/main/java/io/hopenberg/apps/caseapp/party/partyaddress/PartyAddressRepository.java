package io.hopenberg.apps.caseapp.party.partyaddress;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartyAddressRepository extends JpaRepository<PartyAddress, Integer> {
}
