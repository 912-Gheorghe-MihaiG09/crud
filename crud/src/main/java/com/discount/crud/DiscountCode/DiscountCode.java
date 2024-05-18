package com.discount.crud.DiscountCode;

import com.discount.crud.User.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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

    @ManyToOne()
    private User User;

    @Override
    public String toString() {
        return "DiscountCode{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", description='" + description + '\'' +
                ", webSite='" + webSite + '\'' +
                ", siteType='" + siteType + '\'' +
                ", expirationDate=" + expirationDate +
                ", UserId='" + (User != null ? User.getId() : null) + '\'' +
                '}';
    }
}
