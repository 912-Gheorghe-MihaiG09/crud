package com.discount.crud.DiscountCode;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class DiscountCodeService {
    final DiscountCodeRepository discountCodeRepository;

    @Autowired
    public DiscountCodeService(DiscountCodeRepository discountCodeRepository) {
        this.discountCodeRepository = discountCodeRepository;
    }

    public List<DiscountCode> getDiscountCodes(){
        return discountCodeRepository.findAll();
    }

    public DiscountCode addDiscountCode(DiscountCode code){
        return discountCodeRepository.save(code);
    }

    public void deleteDiscountCode(Long discountID) {
        discountCodeRepository.deleteById(discountID);
    }

    @Transactional
    public DiscountCode updateDiscountCode(
            Long discountID, String code,
            String description, String webSite, String siteType,
            String creator, Date expirationDate) {
        DiscountCode discountCode = discountCodeRepository.findById(discountID)
                .orElseThrow(() -> new IllegalStateException("Discount Code with id " + discountID + "not found"));
        System.out.println(code);

        if(code != null && code.length() != 0
                && !Objects.equals(discountCode.getCode(), code)){
            discountCode.setCode(code);
        }

        if(description != null && description.length() != 0
                && !Objects.equals(discountCode.getDescription(), description)){
            discountCode.setDescription(description);
        }

        if(webSite != null && webSite.length() != 0
                && !Objects.equals(discountCode.getWebSite(), webSite)){
            discountCode.setWebSite(webSite);
        }

        if(siteType != null && siteType.length() != 0
                && !Objects.equals(discountCode.getSiteType(), siteType)){
            discountCode.setSiteType(siteType);
        }

        if(creator != null && creator.length() != 0
                && !Objects.equals(discountCode.getCreator(), creator)){
            discountCode.setCreator(creator);
        }

        if(expirationDate != null
                && !Objects.equals(discountCode.getExpirationDate(), expirationDate)){
            discountCode.setExpirationDate(expirationDate);
        }

        return discountCode;
    }
}
