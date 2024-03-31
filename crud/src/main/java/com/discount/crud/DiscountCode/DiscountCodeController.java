package com.discount.crud.DiscountCode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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

    @GetMapping
    List<DiscountCode> getDiscountCodes(@RequestParam(name = "sort", defaultValue = "false", required = false) boolean sort,
                                        @RequestParam(name = "filter", defaultValue = "false", required = false) boolean filter) {
        return discountCodeService.getDiscountCodes(sort, filter);
    }

    @PostMapping
    public DiscountCode addDiscountCode(@RequestBody DiscountCode code) {
        return discountCodeService.addDiscountCode(code);
    }

    @DeleteMapping(path = "/{discountCodeId}")
    public void deleteDiscountCode(@PathVariable("discountCodeId") Long discountID) {
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
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) String expirationDate
    ) {
        Date eD = null;

        if (expirationDate != null && !expirationDate.isEmpty()) {
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                eD = dateFormat.parse(expirationDate);
            } catch (ParseException e) {
                // Handle parsing exception
                e.printStackTrace();
                // You might want to throw an exception or set a default value for expirationDate
            }
        }
        return discountCodeService.updateDiscountCode(discountID, code, description, webSite, siteType, creator, eD);
    }
}
