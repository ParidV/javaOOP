package model;

import java.io.Serializable;
import java.util.Date;

public class product implements Serializable, prodInterface{
	/**
	 * 
	 */
	private static final long serialVersionUID = -333189788924701751L;
	private String name;
	private String gname;
	private supplier supplier;
	private date purchased;
	private date expiration;
	private int sold;
	private int stock;
	private String description;
	private double pprice;
	private double price;
	private category ct;

	@SuppressWarnings("deprecation")
	public product(String name, String gname, supplier supplier, date expiration, int stock, String description, double pprice, double price, category ct) {
		this.name = name;
		this.gname = gname;
		this.supplier = supplier;
		purchased = new date(new Date().getDate(), new Date().getMonth()+1, new Date().getYear()+1900);
		this.expiration = expiration;
		sold = 0;
		this.stock = stock;
		this.description = description;
		this.pprice = pprice;
		this.price = price;
		this.ct = ct;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getPprice() {
		return pprice;
	}

	public void setPprice(double pprice) {
		this.pprice = pprice;
	}

	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGname() {
		return gname;
	}
	public void setGname(String gname) {
		this.gname = gname;
	}
	
	public void sell(int quantity) {
		stock-=quantity;
		sold+=quantity;
	}
	
	
	public supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(supplier supplier) {
		this.supplier = supplier;
	}

	public date getPurchased() {
		return purchased;
	}
	public void setPurchased(date purchased) {
		this.purchased = purchased;
	}
	public date getExpiration() {
		return expiration;
	}
	
	public category getCt() {
		return ct;
	}

	public void setCt(category ct) {
		this.ct = ct;
	}

	public void setExpiration(date expiration) {
		this.expiration = expiration;
	}
	public int getSold() {
		return sold;
	}
	public void setSold(int sold) {
		this.sold = sold;
	}
	@Override
	public String toString() {
		return name + " | " + gname + " | " + supplier + " | " + purchased + " | " + expiration + " | " + sold + " | "
				+ stock + " | " + description + " | " + pprice + " | " + price + " | " + ct.toString();
	}
	
}
