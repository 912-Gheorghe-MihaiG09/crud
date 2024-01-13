package com.discount.crud.DiscountCode;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;


@Configuration
public class DiscountCodeConfig {

    @Bean
    CommandLineRunner commandLineRunner(DiscountCodeRepository repository){
        return args -> {
            DiscountCode firstDiscount = new DiscountCode(
                "SN87CX",
                    "50% discount on summer clothes",
                    "www.clothing.com",
                    "clothes",
                    Date.from(Instant.parse("2025-03-17T10:15:30.00Z")),
                    "Mihai"
            );
            DiscountCode secondDiscount = new DiscountCode(
                "JH772H",
                    "20% discount on smartphones",
                    "www.tech.com",
                    "Tech",
                    Date.from(Instant.parse("2025-07-04T10:15:30.00Z")),
                    "Alex"
            );
            repository.saveAll(List.of(firstDiscount, secondDiscount));
        };
    }
}
