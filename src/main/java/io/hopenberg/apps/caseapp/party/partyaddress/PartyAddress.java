package io.hopenberg.apps.caseapp.party.partyaddress;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.hopenberg.apps.caseapp.party.Party;
import jakarta.persistence.*;

@Entity
@Table(name = "party_addresses")
public class PartyAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int ordinal_id;
    private String city;
    private String postalCode;
    private String street;
    @ManyToOne
    @JoinColumn(name = "party_id")
    @JsonBackReference
    private Party party;

    public PartyAddress() {
    }

    public int getId() {
        return id;
    }

    @JsonIgnore
    public void setId(int id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Party getParty() {
        return party;
    }

    public void setParty(Party party) {
        this.party = party;
    }

    public int getOrdinalId() {
        return ordinal_id;
    }

    @JsonProperty("id")
    public void setOrdinalId(int ordinal_id) {
        this.ordinal_id = ordinal_id;
    }
}
