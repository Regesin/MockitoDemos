package com.mobileapp.service;

import java.util.List;

import com.mobileapp.exceptions.MobileNotFoundException;
import com.mobileapp.model.Mobile;

public interface IMobileService {

	//mock this interface
	
	
	public List<Mobile> showMobiles();

	public List<Mobile> findMobileByBrand(String brand) throws MobileNotFoundException;

	public Mobile findMobileById(int mobileId) throws MobileNotFoundException;
	
	
}