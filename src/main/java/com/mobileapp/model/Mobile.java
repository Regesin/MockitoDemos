package com.mobileapp.model;

public class Mobile {
	private String model;
	private Integer mobileid;
	private String brand;
	private double price;
	public Mobile(String model, Integer mobileid, String brand, double price) {
		super();
		this.model = model;
		this.mobileid = mobileid;
		this.brand = brand;
		this.price = price;
	}
	public Mobile() {
		// TODO Auto-generated constructor stub
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public Integer getMobileid() {
		return mobileid;
	}
	public void setMobileid(int mobileid) {
		this.mobileid = mobileid;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "Mobile [model=" + model + ", mobileid=" + mobileid + ", brand=" + brand + ", price=" + price + "]";
	}
	
	

}