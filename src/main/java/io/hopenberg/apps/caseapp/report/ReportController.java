package io.hopenberg.apps.caseapp.report;

import io.hopenberg.apps.caseapp.caseentity.CaseCountByState;
import io.hopenberg.apps.caseapp.caseentity.CaseEntityRepository;
import io.hopenberg.apps.caseapp.party.PartyAddressesReadModel;
import io.hopenberg.apps.caseapp.party.PartyRepository;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
public class ReportController {

    private final CaseEntityRepository caseRepository;
    private final PartyRepository partyRepository;

    public ReportController(CaseEntityRepository caseRepository,
                            PartyRepository partyRepository) {
        this.caseRepository = caseRepository;
        this.partyRepository = partyRepository;
    }

    @GetMapping("NumberOfCasesByState")
    List<CaseCountByState> getNumberOfCasesByState(
            @RequestParam String caseType,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date dateStart,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date dateEnd
    ) {
        if (dateStart != null && dateEnd != null) {
            return caseRepository.findByDateOfEntryBetweenAndType(dateStart, dateEnd, caseType);
        }
        else {
            return caseRepository.findByType(caseType);
        }
    }

    @GetMapping("AddressesOfActiveSites")
    List<PartyAddressesReadModel> getAddressesOfActiveSites(
            @RequestParam String caseState
    ) {
        return partyRepository.findAllByActiveTrueAndCaseEntity_state(caseState)
                .stream()
                .map(PartyAddressesReadModel::new)
                .toList();
    }
}
