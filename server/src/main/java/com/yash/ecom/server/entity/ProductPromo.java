package com.yash.ecom.server.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "product_promo", schema = "public", catalog = "e_com_db2")
public class ProductPromo {
    private String productPromoId;
    private String promoName;
    private String productPromoCode;
    private String useLimitPerCode;
    private String useLimitPerCustomer;
    private Date fromDate;
    private Date thruDate;
    private String productPromoCond;
    private String productPromoAction;

    @Id
    @Column(name = "productpromoid", nullable = false, length = 20)
    public String getProductPromoId() {
        return productPromoId;
    }

    public void setProductPromoId(String productpromoid) {
        this.productPromoId = productpromoid;
    }

    @Basic
    @Column(name = "promoname", nullable = true, length = 50)
    public String getPromoName() {
        return promoName;
    }

    public void setPromoName(String promoname) {
        this.promoName = promoname;
    }

    @Basic
    @Column(name = "productpromocode", nullable = true, length = 20)
    public String getProductPromoCode() {
        return productPromoCode;
    }

    public void setProductPromoCode(String productpromocode) {
        this.productPromoCode = productpromocode;
    }

    @Basic
    @Column(name = "uselimitpercode", nullable = true, length = 20)
    public String getUseLimitPerCode() {
        return useLimitPerCode;
    }

    public void setUseLimitPerCode(String uselimitpercode) {
        this.useLimitPerCode = uselimitpercode;
    }

    @Basic
    @Column(name = "uselimitpercustomer", nullable = true, length = 20)
    public String getUseLimitPerCustomer() {
        return useLimitPerCustomer;
    }

    public void setUseLimitPerCustomer(String uselimitpercustomer) {
        this.useLimitPerCustomer = uselimitpercustomer;
    }

    @Basic
    @Column(name = "fromdate", nullable = true)
    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromdate) {
        this.fromDate = fromdate;
    }

    @Basic
    @Column(name = "thrudate", nullable = true)
    public Date getThruDate() {
        return thruDate;
    }

    public void setThruDate(Date thrudate) {
        this.thruDate = thrudate;
    }

    @Basic
    @Column(name = "productpromocond", nullable = true, length = 50)
    public String getProductPromoCond() {
        return productPromoCond;
    }

    public void setProductPromoCond(String productpromocond) {
        this.productPromoCond = productpromocond;
    }

    @Basic
    @Column(name = "productpromoaction", nullable = true, length = 50)
    public String getProductPromoAction() {
        return productPromoAction;
    }

    public void setProductPromoAction(String productpromoaction) {
        this.productPromoAction = productpromoaction;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductPromo that = (ProductPromo) o;
        return Objects.equals(productPromoId, that.productPromoId) &&
                Objects.equals(promoName, that.promoName) &&
                Objects.equals(productPromoCode, that.productPromoCode) &&
                Objects.equals(useLimitPerCode, that.useLimitPerCode) &&
                Objects.equals(useLimitPerCustomer, that.useLimitPerCustomer) &&
                Objects.equals(fromDate, that.fromDate) &&
                Objects.equals(thruDate, that.thruDate) &&
                Objects.equals(productPromoCond, that.productPromoCond) &&
                Objects.equals(productPromoAction, that.productPromoAction);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productPromoId, promoName, productPromoCode, useLimitPerCode, useLimitPerCustomer, fromDate, thruDate, productPromoCond, productPromoAction);
    }
}
