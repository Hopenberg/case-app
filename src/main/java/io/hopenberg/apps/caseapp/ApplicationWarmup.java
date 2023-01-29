package io.hopenberg.apps.caseapp;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.hopenberg.apps.caseapp.caseentity.CaseEntity;
import io.hopenberg.apps.caseapp.caseentity.CaseEntityRepository;
import io.hopenberg.apps.caseapp.party.Party;
import io.hopenberg.apps.caseapp.party.partyaddress.PartyAddressRepository;
import io.hopenberg.apps.caseapp.party.PartyRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Component
public class ApplicationWarmup implements ApplicationListener<ContextRefreshedEvent> {
    private final CaseEntityRepository caseEntityRepository;
    private final PartyRepository partyRepository;
    private final PartyAddressRepository addressRepository;

    public ApplicationWarmup(CaseEntityRepository caseEntityRepository,
                             PartyRepository partyRepository,
                             PartyAddressRepository addressRepository) {
        this.caseEntityRepository = caseEntityRepository;
        this.partyRepository = partyRepository;
        this.addressRepository = addressRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        try (InputStream is = ApplicationWarmup.class.getResourceAsStream("/CaseEntity2.json")) {
            ObjectMapper mapper = new ObjectMapper();
            List<CaseEntity> caseEntities = mapper.readValue(is, new TypeReference<List<CaseEntity>>(){});
            caseEntities.forEach(caseEntityRepository::save);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try (InputStream is = ApplicationWarmup.class.getResourceAsStream("/PartyEntity2.json")) {
            ObjectMapper mapper = new ObjectMapper();
            List<Party> parties = mapper.readValue(is, new TypeReference<List<Party>>(){});
            parties.forEach(party -> {
                party.setCaseEntity(caseEntityRepository.findById(party.getCaseId()).orElse(null));
                partyRepository.save(party);
                party.getPartyAddresses().forEach(addr -> {
                    addr.setParty(party);
                    addressRepository.save(addr);
                });
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
