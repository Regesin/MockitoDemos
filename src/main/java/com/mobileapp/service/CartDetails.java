package com.mobileapp.service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.mobileapp.exceptions.EmptyCartException;
import com.mobileapp.exceptions.MobileNotFoundException;
import com.mobileapp.model.Mobile;

public class CartDetails {
	ICartService cartService;

	public void setCartService(ICartService cartService) {
		this.cartService = cartService;
	}

	public List<Mobile> showCart() throws EmptyCartException {
		List<Mobile> mobileList;

		try {
			mobileList = cartService.showCart();
		} catch (EmptyCartException e) {
			throw e;
		}

		if (mobileList != null) {
			return mobileList.stream().sorted(Comparator.comparing(Mobile::getMobileid)).collect(Collectors.toList());
		}

		return mobileList;
	}

	public String addtoCart(Mobile mobile) throws MobileNotFoundException {
		cartService.addtoCart(mobile);

		return "added successfully";
	}

	public boolean removeFromCart(Mobile mobile) throws  EmptyCartException {
		boolean result = false;
		try {
			result = cartService.removeFromCart(mobile);

		}catch (EmptyCartException e) {
			throw e;
		}
		return result;

	}

}
