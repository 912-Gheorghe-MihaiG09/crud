package com.discount.crud;

import com.discount.crud.DiscountCode.DiscountCode;
import com.discount.crud.DiscountCode.InMemoryDiscountCodeRepository;
import com.discount.crud.User.User;
import com.discount.crud.User.auth.RegisterRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static com.discount.crud.User.Role.USER;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class InMemoryDiscountCodeRepositoryTest {

    private InMemoryDiscountCodeRepository discountCodeRepository;

    @Mock
    private DiscountCode mockDiscountCode;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        discountCodeRepository = new InMemoryDiscountCodeRepository();
    }

    @Test
    public void testSave() {
        when(mockDiscountCode.getId()).thenReturn(null);
        DiscountCode savedDiscountCode = discountCodeRepository.save(mockDiscountCode);
        assertEquals(null, savedDiscountCode.getId());
    }

    @Test
    public void testFindAll() {
        User user1 = User.builder()
                .firstname("test")
                .lastname("test")
                .email("test@test.com")
                .password("password")
                .role(USER)
                .build();
        DiscountCode code1 = new DiscountCode(1L, "CODE1", "Description 1", "website1.com", "type1", new Date(), user1);
        DiscountCode code2 = new DiscountCode(2L, "Code2", "Description 2", "website2.com", "type2", new Date(), user1);
        discountCodeRepository.save(code1);
        discountCodeRepository.save(code2);
        List<DiscountCode> allDiscountCodes = discountCodeRepository.findAll();
        assertEquals(2, allDiscountCodes.size());
    }

    @Test
    public void testFindByExpirationDateGreaterThan() {
        User user1 = User.builder()
                .firstname("test")
                .lastname("test")
                .email("test@test.com")
                .password("password")
                .role(USER)
                .build();
        Date currentDate = new Date();
        Date futureDate = new Date(currentDate.getTime() + 86400000); // Adding one day (in milliseconds)
        DiscountCode code1 = new DiscountCode(1L,"CODE1", "Description 1", "website1.com", "type1", currentDate, user1);
        DiscountCode code2 = new DiscountCode(2L,"CODE2", "Description 2", "website2.com", "type2", futureDate, user1);
        discountCodeRepository.save(code1);
        discountCodeRepository.save(code2);
        List<DiscountCode> filteredCodes = discountCodeRepository.findByExpirationDateGreaterThan(currentDate);
        assertEquals(1, filteredCodes.size());
        assertEquals(code2, filteredCodes.get(0));
    }

    @Test
    public void testFindById() {
        when(mockDiscountCode.getId()).thenReturn(1L);
        discountCodeRepository.save(mockDiscountCode);
        Optional<DiscountCode> foundDiscountCode = discountCodeRepository.findById(1L);
        assertEquals(mockDiscountCode, foundDiscountCode.orElse(null));
    }

    @Test
    public void testDeleteById() {
        when(mockDiscountCode.getId()).thenReturn(1L);
        discountCodeRepository.save(mockDiscountCode);
        discountCodeRepository.deleteById(1L);
        Optional<DiscountCode> deletedDiscountCode = discountCodeRepository.findById(1L);
        assertEquals(Optional.empty(), deletedDiscountCode);
    }
}
