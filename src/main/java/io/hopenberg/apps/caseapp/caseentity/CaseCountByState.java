package io.hopenberg.apps.caseapp.caseentity;

import com.fasterxml.jackson.databind.jsonschema.JsonSerializableSchema;

import java.io.Serializable;

public class CaseCountByState implements Serializable {
    private String state;
    private Long total;

    public CaseCountByState(String state, Long total) {
        this.state = state;
        this.total = total;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }
}
