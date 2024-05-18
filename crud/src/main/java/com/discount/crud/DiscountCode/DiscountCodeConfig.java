package com.discount.crud.DiscountCode;

import com.discount.crud.User.User;
import com.discount.crud.User.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Instant;
import java.util.Date;


@Configuration
public class DiscountCodeConfig {

    @Bean
    CommandLineRunner commandLineRunner(DiscountCodeRepository repository, UserRepository UserRepository){
        return args -> {
//            User firstUser = User.builder().name("Mihai").build();
//            User secondUser = User.builder().name("Alex").build();
//            firstUser = UserRepository.save(firstUser);
//            secondUser = UserRepository.save(secondUser);
//            DiscountCode firstDiscount = new DiscountCode(
//                    0L,
//                "SN87CX",
//                    "50% discount on summer clothes",
//                    "www.clothing.com",
//                    "clothes",
//                    Date.from(Instant.parse("2025-03-17T10:15:30.00Z")),
//                    firstUser
//            );
//            DiscountCode secondDiscount = new DiscountCode(
//                    0L,
//                "JH772H",
//                    "20% discount on smartphones",
//                    "www.tech.com",
//                    "Tech",
//                    Date.from(Instant.parse("2025-07-04T10:15:30.00Z")),
//                    secondUser
//            );
//            repository.save(firstDiscount);
//            repository.save(secondDiscount);
        };
    }
}
