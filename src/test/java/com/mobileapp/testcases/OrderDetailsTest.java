package com.mobileapp.testcases;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.mobileapp.exceptions.MobileNotFoundException;
import com.mobileapp.model.Mobile;
import com.mobileapp.service.IMobileService;
import com.mobileapp.service.OrderDetails;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
class OrderDetailsTest {

	@Mock
	IMobileService mobileService;

//	create an object of orderDetails
	@InjectMocks
	OrderDetails orderDetails;
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
		mobile2 = new Mobile("Xiaomi", 2, "A3", 16000);
		mobile3 = new Mobile("realme", 3, "Neo2", 32000);
		mobile4 = new Mobile("Xiaomi", 4, "3s", 10000);
		mobile5 = new Mobile("Xiaomi", 5, "4a", 10000);
		mobile6 = new Mobile("Samsung", 6, "S21", 53000);
		orderDetails = new OrderDetails();
		orderDetails.setMobileService(mobileService);
		List<Mobile> mobileData = Arrays.asList(mobile1, mobile2, mobile3, mobile4, mobile5, mobile6);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testShowMobiles() throws MobileNotFoundException {
		String brand = "Samsung";
		List<Mobile> expectedMobiles = Arrays.asList(mobile6, mobile1);
//		use the proxy object to call the method of IMobileService with when then
		Mockito.when(mobileService.findMobileByBrand("Samsung")).thenReturn(Arrays.asList(mobile1, mobile6));
		List<Mobile> actualMobileList = orderDetails.showMobiles(brand);
		assertEquals(expectedMobiles, actualMobileList, "List not equal");

	}

	@Test
	void testshowMobilesInvalid() throws MobileNotFoundException {
		Mockito.when(mobileService.findMobileByBrand("vivo")).thenThrow(MobileNotFoundException.class);
		assertThrows(MobileNotFoundException.class, () -> orderDetails.showMobiles("vivo"));
	}

	@Test
	void testShowMobilesNull() throws MobileNotFoundException {
		String brand = "Samsung";

		Mockito.when(mobileService.findMobileByBrand("Samsung")).thenReturn(null);

		List<Mobile> actualMobileList = orderDetails.showMobiles(brand);
		assertNull(actualMobileList);

	}

	@Test
	void testShowMobilesEmpty() throws MobileNotFoundException {
		String brand = "Moto";

		Mockito.when(mobileService.findMobileByBrand("Moto")).thenReturn(new ArrayList<>());

		List<Mobile> actualMobileList = orderDetails.showMobiles(brand);
		assertEquals(0, actualMobileList.size(), "List should be empty");

	}

	@Test
	void testShowMobilesSortByBrand() throws MobileNotFoundException {
		String brand = "Samsung";
		List<Mobile> expectedMobiles = Arrays.asList(mobile6, mobile1);

		Mockito.when(mobileService.findMobileByBrand("Samsung")).thenReturn(Arrays.asList(mobile1, mobile6));
		List<Mobile> actualMobileList = orderDetails.showMobiles(brand);

		assertEquals(expectedMobiles, actualMobileList, "List not equal");

	}

	@Test
	void testOrderMobileOne() throws MobileNotFoundException {
		String expected = "mobile ordered";
		when(mobileService.findMobileById(1)).thenReturn(mobile1);
		String actual = orderDetails.orderMobile(1);
		assertEquals(expected, actual, "Ordered");

	}

	@Test
	void testOrderInvalid() throws MobileNotFoundException {
		String expected = "mobile not ordered";
		when(mobileService.findMobileById(100)).thenReturn(null);
		String actual = orderDetails.orderMobile(100);
		assertEquals(expected, actual, "values are different");

	}

	@Test
	@DisplayName("Checking expection for mock object")
	void testOrderException() throws MobileNotFoundException {
		String expected = "mobile not ordered";
		when(mobileService.findMobileById(100)).thenThrow(MobileNotFoundException.class);
		String actual = orderDetails.orderMobile(100);
		assertEquals(expected, actual, "values are not same");

	}

	@Test
	@DisplayName("Checking with empty object")
	void testOrderEmpty() throws MobileNotFoundException {
		String expected = "mobile not ordered";
		when(mobileService.findMobileById(100)).thenReturn(new Mobile());
		String actual = orderDetails.orderMobile(100);
		assertEquals(expected, actual, "empty object expected");

	}

}