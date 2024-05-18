package com.discount.crud.DiscountCode;

import com.discount.crud.User.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface DiscountCodeRepository extends JpaRepository<DiscountCode, Long> {
    List<DiscountCode> findByExpirationDateGreaterThan(Date date);
}
