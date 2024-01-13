package com.discount.crud.DiscountCode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/discount")
public class DiscountCodeController {
    final DiscountCodeService discountCodeService;

    @Autowired
    public DiscountCodeController(DiscountCodeService discountCodeService) {
        this.discountCodeService = discountCodeService;
    }

    @GetMapping()
    List<DiscountCode> getDiscountCodes(){
        return discountCodeService.getDiscountCodes();
    }

    @PostMapping
    public DiscountCode addDiscountCode(@RequestBody DiscountCode code){
        return discountCodeService.addDiscountCode(code);
    }

    @DeleteMapping(path = "/{discountCodeId}")
    public void deleteDiscountCode(@PathVariable("discountCodeId") Long discountID){
        discountCodeService.deleteDiscountCode(discountID);
    }

    @PutMapping(path = "/{discountCodeId}")
    public DiscountCode updateDiscountCode(
            @PathVariable("discountCodeId") Long discountID,
            @RequestParam(required = false) String code,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) String webSite,
            @RequestParam(required = false) String siteType,
            @RequestParam(required = false) String creator,
            @RequestParam(required = false) Date expirationDate
            ){
        return discountCodeService.updateDiscountCode(discountID, code, description, webSite, siteType, creator, expirationDate);
    }
}
