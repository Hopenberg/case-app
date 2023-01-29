package io.hopenberg.apps.caseapp.caseentity;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.hopenberg.apps.caseapp.party.Party;
import jakarta.persistence.*;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "cases")
public class CaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String number;
    private String type;
    private String state;
    @Temporal(TemporalType.DATE)
    private Date dateOfEntry;
    @OneToMany(mappedBy = "caseEntity")
    private Set<Party> parties;

    public CaseEntity() {
    }

    public int getId() {
        return id;
    }

    @JsonProperty("caseId")
    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    @JsonProperty("caseNumber")
    public void setNumber(String number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }

    @JsonProperty("caseType")
    public void setType(String type) {
        this.type = type;
    }

    public String getState() {
        return state;
    }

    @JsonProperty("caseState")
    public void setState(String state) {
        this.state = state;
    }

    public Date getDateOfEntry() {
        return dateOfEntry;
    }

    public void setDateOfEntry(Date dateOfEntry) {
        this.dateOfEntry = dateOfEntry;
    }
}
