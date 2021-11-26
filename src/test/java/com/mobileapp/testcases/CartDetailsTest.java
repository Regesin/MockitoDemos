package com.mobileapp.testcases;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.mobileapp.exceptions.EmptyCartException;
import com.mobileapp.exceptions.MobileNotFoundException;
import com.mobileapp.model.Mobile;
import com.mobileapp.service.CartDetails;
import com.mobileapp.service.ICartService;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)

class CartDetailsTest {

	@Mock
	ICartService cartService;

	@InjectMocks
	CartDetails cartDetails;
	Mobile mobile1, mobile2, mobile3, mobile4, mobile5, mobile6;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		mobile1 = new Mobile("Samsung", 1, "A53", 26000);
		mobile2 = new Mobile("Xiaomi", 2, "B3", 16000);
		mobile3 = new Mobile("realme", 3, "Neo2", 32000);
		mobile4 = new Mobile("Xiaomi", 4, "C3s", 10000);
		mobile5 = new Mobile("Xiaomi", 5, "4a", 10000);
		mobile6 = new Mobile("Samsung", 6, "S21", 53000);
		List<Mobile> mobileData = Arrays.asList(mobile1, mobile2, mobile3, mobile4, mobile5, mobile6);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testAddcart() throws MobileNotFoundException {
		doNothing().when(cartService).addtoCart(mobile1);
		String actual = cartDetails.addtoCart(mobile1);
		String expected = "added successfully";
		assertEquals(expected, actual, "Invalid");
	}

	@Test
	void testAddCartException() throws MobileNotFoundException {
		doThrow(new MobileNotFoundException("Invalid")).when(cartService).addtoCart(mobile1);
		assertThrows(MobileNotFoundException.class, () -> cartDetails.addtoCart(mobile1));
	}

	@Test
	void testAddCart() throws MobileNotFoundException, EmptyCartException {
		List<Mobile> expectedMobile = Arrays.asList(mobile1, mobile2, mobile4);
		doReturn(Arrays.asList(mobile4, mobile1, mobile2)).when(cartService).showCart();
		List<Mobile> actualMobiles = cartDetails.showCart();
		assertEquals(expectedMobile, actualMobiles, "not equal");
	}

	@Test
	void testShowCartEmpty() throws MobileNotFoundException, EmptyCartException {
		doThrow(new EmptyCartException()).when(cartService).showCart();
		assertThrows(EmptyCartException.class, () -> cartDetails.showCart());
	}

	@Test
	void testAddCartNull() throws MobileNotFoundException, EmptyCartException {
		doReturn(null).when(cartService).showCart();
		assertNull(cartDetails.showCart());
	}

	@Test
	void testRemoveCart() throws EmptyCartException, MobileNotFoundException {
		doReturn(true).when(cartService).removeFromCart(mobile1);
		assertEquals(true, cartDetails.removeFromCart(mobile1));
	}

	@Test
	void testRemoveCartEmpty() throws EmptyCartException {
		doThrow(new EmptyCartException()).when(cartService).removeFromCart(mobile3);
		assertThrows(EmptyCartException.class, () -> cartDetails.removeFromCart(mobile3));
	}

	@Test
	void testRemoveCartNegative() throws EmptyCartException, MobileNotFoundException {
		doReturn(false).when(cartService).removeFromCart(mobile1);
		assertEquals(false, cartDetails.removeFromCart(mobile1));
	}
}
