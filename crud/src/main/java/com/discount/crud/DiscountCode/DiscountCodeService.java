package com.discount.crud.DiscountCode;

import com.discount.crud.User.User;
import com.discount.crud.User.UserRepository;
import com.github.javafaker.Faker;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.*;
import java.util.concurrent.TimeUnit;

@CrossOrigin(maxAge = 3600)
@Service
public class DiscountCodeService {
    final DiscountCodeRepository discountCodeRepository;
    final UserRepository UserRepository;

    @Autowired
    public DiscountCodeService(DiscountCodeRepository discountCodeRepository, UserRepository UserRepository) {
        this.discountCodeRepository = discountCodeRepository;
        this.UserRepository = UserRepository;
    }

    public List<DiscountCode> getDiscountCodes(boolean sort, boolean filter){
        List<DiscountCode> initialList;
        if(filter)
            initialList = this.discountCodeRepository.findByExpirationDateGreaterThan(new Date());
        else
            initialList = this.discountCodeRepository.findAll();
        if(sort)
            initialList.sort(
                    Comparator.comparing(DiscountCode::getExpirationDate)
            );
        return initialList;
    }

    public DiscountCode addDiscountCode(DiscountCodeDTO code){
//        Optional<User> UserEntity = UserRepository.findByName(code.getUser());
//        if(UserEntity.isEmpty()){
//            UserEntity = Optional.of(UserRepository.save(User.builder().name(code.getUser()).build()));
//        }
//        DiscountCode discountCode = DiscountCode.builder().expirationDate(code.getExpirationDate()).code(code.getCode()).
//                webSite(code.getWebSite()).description(code.getDescription()).User(UserEntity.get()).build();
//        return discountCodeRepository.save(discountCode);
        return null;
    }

    public void deleteDiscountCode(Long discountID) {
        discountCodeRepository.deleteById(discountID);
    }

    @Transactional
    public DiscountCode updateDiscountCode(
            Long discountID, String code,
            String description, String webSite, String siteType,
            String User, Date expirationDate) {
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

//        if(User != null && User.length() != 0
//                && !Objects.equals(discountCode.getUser(), User)){
//            Optional<User> UserEntity = UserRepository.findByName(User);
//            if(UserEntity.isEmpty()){
//                UserEntity = Optional.of(UserRepository.save(User.builder().name(User).build()));
//            }
//            discountCode.setUser(UserEntity.get());
//        }

        if(expirationDate != null
                && !Objects.equals(discountCode.getExpirationDate(), expirationDate)){
            discountCode.setExpirationDate(expirationDate);
        }

        return discountCode;
    }

    public List<DiscountCode> generateFakeDiscountCodes(int count) {
        List<DiscountCode> fakeDiscountCodes = new ArrayList<>();
        Faker faker = new Faker();

        List<User> users = UserRepository.findAll();
        Random random = new Random();

        for (int i = 0; i < count; i++) {
            String username = faker.name().username();
           User user = users.get(random.nextInt(0, users.size()));
            DiscountCode fakeCode = new DiscountCode(
                    0L,
                    faker.lorem().word(), // Fake code
                    faker.lorem().sentence(), // Fake description
                    faker.internet().url(), // Fake website
                    "Online",
                    faker.date().future(365, TimeUnit.DAYS), // Fake expiration date within the next year// Fake site type (You can change it if needed)
                    user // Fake User
            );
            fakeDiscountCodes.add(fakeCode);
            discountCodeRepository.save(fakeCode);
        }

        return fakeDiscountCodes;
    }

    public Map<User, Integer> getUserDiscounts(User user) {
        List<User> UserEntity = UserRepository.findAll();
        Map<User, Integer> UserDiscounts = new HashMap<>();
//        for(User User : UserEntity){
//            List<DiscountCode> discountCodes = discountCodeRepository.findAllByUser(User);
//            UserDiscounts.put(User, discountCodes.size());
//        }
        return UserDiscounts;
    }
}
