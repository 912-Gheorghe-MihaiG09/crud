package com.discount.crud;

import com.discount.crud.DiscountCode.DiscountCode;
import com.discount.crud.DiscountCode.DiscountCodeRepository;
import com.discount.crud.DiscountCode.DiscountCodeService;
import com.discount.crud.User.User;
import com.discount.crud.User.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class DiscountCodeServiceTest {

    private DiscountCodeService discountCodeService;

    @Mock
    private DiscountCodeRepository mockRepository;
    @Mock
    private UserRepository mockUserRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        discountCodeService = new DiscountCodeService(mockRepository, mockUserRepository);
    }

    @Test
    public void testGetDiscountCodes() {
        List<DiscountCode> mockList = new ArrayList<>();
        when(mockRepository.findAll()).thenReturn(mockList);

        // Test without sorting and filtering
        assertEquals(mockList, discountCodeService.getDiscountCodes(false, false));

        // Test with sorting and filtering
        when(mockRepository.findByExpirationDateGreaterThan(any(Date.class))).thenReturn(mockList);
        assertEquals(mockList, discountCodeService.getDiscountCodes(true, true));
    }

    @Test
    public void testAddDiscountCode() {
//        DiscountCode mockCode = new DiscountCodeDTO(0L, "CODE1", "Description", "website.com", "type", new Date(), "User");
//        when(mockRepository.save(mockCode)).thenReturn(mockCode);
//        assertEquals(mockCode, discountCodeService.addDiscountCode(mockCode));
    }

    @Test
    public void testDeleteDiscountCode() {
        Long discountId = 1L;
        discountCodeService.deleteDiscountCode(discountId);
        verify(mockRepository, times(1)).deleteById(discountId);
    }

    @Test
    public void testUpdateDiscountCode() {
//        Long discountId = 1L;
//        DiscountCode mockCode = new DiscountCode(0L, "CODE1", "Description", "website.com", "type", new Date(), User.builder().name("User").build());
//        when(mockRepository.findById(discountId)).thenReturn(Optional.of(mockCode));
//
//        // Update code
//        String newCode = "NEWCODE";
//        DiscountCode updatedCode = discountCodeService.updateDiscountCode(discountId, newCode, null, null, null, null, null);
//        assertEquals(newCode, updatedCode.getCode());
//        mockRepository.save(updatedCode);
//
//        // Verify repository save is called
//        verify(mockRepository, times(1)).save(mockCode);
    }

    @Test
    public void testUpdateDiscountCodeNotFound() {
        Long discountId = 1L;
        when(mockRepository.findById(discountId)).thenReturn(Optional.empty());

        // Ensure exception is thrown when the discount code is not found
        assertThrows(IllegalStateException.class, () -> discountCodeService.updateDiscountCode(discountId, null, null, null, null, null, null));
    }
}
