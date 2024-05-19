package com.discount.crud.DiscountCode;

import com.discount.crud.User.User;
import lombok.Data;

import java.util.Date;

@Data
public class DiscountCodeDTO {
    private Long id;
    private String code;
    private String description;
    private String webSite;
    private String siteType;
    private Date expirationDate;
    private String user;


    public static DiscountCodeDTO fromDiscountCode(DiscountCode discountCode) {
        DiscountCodeDTO dto = new DiscountCodeDTO();
        dto.setId(discountCode.getId());
        dto.setCode(discountCode.getCode());
        dto.setDescription(discountCode.getDescription());
        dto.setWebSite(discountCode.getWebSite());
        dto.setSiteType(discountCode.getSiteType());
        dto.setExpirationDate(discountCode.getExpirationDate());
        dto.setUser(discountCode.getUser().getUsername());
        return dto;
    }
}