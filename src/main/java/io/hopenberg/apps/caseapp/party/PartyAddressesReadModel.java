package io.hopenberg.apps.caseapp.party;

import io.hopenberg.apps.caseapp.party.partyaddress.PartyAddress;

import java.util.Comparator;
import java.util.List;

public class PartyAddressesReadModel {

    private final int partyId;
    private final List<PartyAddress> partyAddresses;

    public PartyAddressesReadModel(Party source) {
        this.partyId = source.getId();
        this.partyAddresses = source.getPartyAddresses()
                .stream()
                .sorted(Comparator.comparingInt(PartyAddress::getOrdinalId))
                .toList();
    }

    public int getPartyId() {
        return partyId;
    }

    public List<PartyAddress> getPartyAddresses() {
        return partyAddresses;
    }
}
