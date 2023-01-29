package io.hopenberg.apps.caseapp.party;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.hopenberg.apps.caseapp.caseentity.CaseEntity;
import io.hopenberg.apps.caseapp.party.partyaddress.PartyAddress;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "parties")
public class Party {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "case_id")
    private CaseEntity caseEntity;
    @Transient
    private int caseId;
    private String partyType;
    private String name;
    private boolean active;
    @OneToMany(mappedBy = "party")
    @JsonManagedReference
    private Set<PartyAddress> partyAddresses;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPartyType() {
        return partyType;
    }

    public void setPartyType(String partyType) {
        this.partyType = partyType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Set<PartyAddress> getPartyAddresses() {
        return partyAddresses;
    }

    @JsonProperty("addresses")
    public void setPartyAddresses(Set<PartyAddress> partyAddresses) {
        this.partyAddresses = partyAddresses;
    }

    public CaseEntity getCaseEntity() {
        return caseEntity;
    }

    public void setCaseEntity(CaseEntity caseEntity) {
        this.caseEntity = caseEntity;
    }

    public int getCaseId() {
        return caseId;
    }

    @JsonProperty("caseId")
    public void setCaseId(int caseId) {
        this.caseId = caseId;
    }
}
