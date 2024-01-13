package com.discount.crud.DiscountCode;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table
public class DiscountCode {
    @Id
    @SequenceGenerator(
            name = "discount_code_sequence",
            sequenceName = "discount_code_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "discount_code_sequence"
    )
    private Long id;
    private String code;
    private String description;
    private String webSite;
    private String siteType;
    private Date expirationDate;
    private String creator;

    public DiscountCode(Long id, String code, String description, String webSite, String siteType, Date expirationDate, String creator) {
        this.id = id;
        this.code = code;
        this.description = description;
        this.webSite = webSite;
        this.siteType = siteType;
        this.expirationDate = expirationDate;
        this.creator = creator;
    }

    public DiscountCode(){}

    public DiscountCode(String code, String description, String webSite, String siteType, Date expirationDate, String creator) {
        this.code = code;
        this.description = description;
        this.webSite = webSite;
        this.siteType = siteType;
        this.expirationDate = expirationDate;
        this.creator = creator;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getWebSite() {
        return webSite;
    }

    public void setWebSite(String webSite) {
        this.webSite = webSite;
    }

    public String getSiteType() {
        return siteType;
    }

    public void setSiteType(String siteType) {
        this.siteType = siteType;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    @Override
    public String toString() {
        return "DiscountCode{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", description='" + description + '\'' +
                ", webSite='" + webSite + '\'' +
                ", siteType='" + siteType + '\'' +
                ", expirationDate=" + expirationDate +
                ", creator='" + creator + '\'' +
                '}';
    }
}
