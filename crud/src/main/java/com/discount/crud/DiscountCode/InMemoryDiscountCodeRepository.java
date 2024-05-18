package com.discount.crud.DiscountCode;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryDiscountCodeRepository {
    private final List<DiscountCode> discountCodes = new ArrayList<>();
    private long nextId = 1;

    public List<DiscountCode> findAll() {
        return discountCodes;
    }

    public List<DiscountCode> findByExpirationDateGreaterThan(Date date) {
        List<DiscountCode> filteredCodes = new ArrayList<>();
        for (DiscountCode code : discountCodes) {
            if (code.getExpirationDate().after(date)) {
                filteredCodes.add(code);
            }
        }
        return filteredCodes;
    }

    public Optional<DiscountCode> findById(Long id) {
        return discountCodes.stream()
                .filter(code -> code.getId().equals(id))
                .findFirst();
    }

    public DiscountCode save(DiscountCode code) {
        if (code.getId() == null) {
            code.setId(nextId++);
        }
        discountCodes.add(code);
        return code;
    }

    public void deleteById(Long id) {
        discountCodes.removeIf(code -> code.getId().equals(id));
    }
}
