package com.yash.ecom.server.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "order_role", schema = "public", catalog = "e_com_db")
@IdClass(OrderRolePK.class)
public class OrderRole {
    private String orderId;
    private String partyId;
    private String roleTypeId;

    @Id
    @Column(name = "orderid", nullable = false, length = 20)
    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderid) {
        this.orderId = orderid;
    }

    @Id
    @Column(name = "partyid", nullable = false, length = 20)
    public String getPartyId() {
        return partyId;
    }

    public void setPartyId(String partyid) {
        this.partyId = partyid;
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
        OrderRole orderRole = (OrderRole) o;
        return Objects.equals(orderId, orderRole.orderId) &&
                Objects.equals(partyId, orderRole.partyId) &&
                Objects.equals(roleTypeId, orderRole.roleTypeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, partyId, roleTypeId);
    }
}
