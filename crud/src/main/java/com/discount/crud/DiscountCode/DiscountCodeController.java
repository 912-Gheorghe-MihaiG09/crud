package com.discount.crud.DiscountCode;

import com.discount.crud.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/discount")
public class DiscountCodeController {
    final DiscountCodeService discountCodeService;

    @Autowired
    public DiscountCodeController(DiscountCodeService discountCodeService) {
        this.discountCodeService = discountCodeService;
    }

    @GetMapping
    List<DiscountCodeDTO> getDiscountCodes(@RequestParam(name = "sort", defaultValue = "false", required = false) boolean sort,
                                        @RequestParam(name = "filter", defaultValue = "false", required = false) boolean filter) {
        return discountCodeService.getDiscountCodes(sort, filter).stream().map((DiscountCodeDTO::fromDiscountCode)).toList();
    }

    @PostMapping
    public DiscountCodeDTO addDiscountCode(@RequestBody DiscountCodeDTO code) {
        return DiscountCodeDTO.fromDiscountCode(discountCodeService.addDiscountCode(code));
    }

    @DeleteMapping(path = "/{discountCodeId}")
    public void deleteDiscountCode(@PathVariable("discountCodeId") Long discountID) {
        discountCodeService.deleteDiscountCode(discountID);
    }

    @PutMapping(path = "/{discountCodeId}")
    public DiscountCodeDTO updateDiscountCode(
            @PathVariable("discountCodeId") Long discountID,
            @RequestParam(required = false) String code,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) String webSite,
            @RequestParam(required = false) String siteType,
            @RequestParam(required = false) String User,
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
        return DiscountCodeDTO
                .fromDiscountCode(discountCodeService.updateDiscountCode(
                        discountID, code, description, webSite, siteType, User, eD));
    }

    @PostMapping("/generate")
    public List<DiscountCodeDTO> generateDiscountCodes(@RequestParam Integer count) {
        System.out.printf(String.valueOf(count));
        return discountCodeService.generateFakeDiscountCodes(count).stream()
                .map((DiscountCodeDTO::fromDiscountCode)).toList();
    }

//    @GetMapping("/user")
//    public List<DiscountCodeDTO> getUserDiscountCodes() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        User userDetails = (User) authentication.getPrincipal();
//        System.out.println(userDetails.getUsername());
//        return discountCodeService.getUserDiscounts(user);
//    }
}
