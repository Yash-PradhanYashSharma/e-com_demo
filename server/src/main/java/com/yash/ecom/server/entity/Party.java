package com.yash.ecom.server.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Party {
    private String partyId;
    private Boolean statusId;
    private String roleTypeId;

    @Id
    @Column(name = "partyid", nullable = false, length = 20)
    public String getPartyId() {
        return partyId;
    }

    public void setPartyId(String partyid) {
        this.partyId = partyid;
    }

    @Basic
    @Column(name = "statusid", nullable = true)
    public Boolean getStatusId() {
        return statusId;
    }

    public void setStatusId(Boolean statusid) {
        this.statusId = statusid;
    }

    @Basic
    @Column(name = "roletypeid", nullable = true, length = 20)
    public String getRoleTypeId() {
        return roleTypeId;
    }

    public void setRoleTypeId(String roletypeid) {
        this.roleTypeId = roletypeid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Party party = (Party) o;
        return Objects.equals(partyId, party.partyId) &&
                Objects.equals(statusId, party.statusId) &&
                Objects.equals(roleTypeId, party.roleTypeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(partyId, statusId, roleTypeId);
    }
}
