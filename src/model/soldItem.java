package model;

public class soldItem {
	private String itemName;
	private int quantity;
	private double itemPrice;
	private double totalPrice;
	private int arrindex;
	public soldItem(int arrindex, String itemName, int quantity, double itemPrice) {
		this.arrindex = arrindex;
		this.itemName = itemName;
		this.quantity = quantity;
		this.itemPrice = itemPrice;
		totalPrice = quantity*itemPrice;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public int getArrindex() {
		return arrindex;
	}
	public void setArrindex(int arrindex) {
		this.arrindex = arrindex;
	}
	public double getItemPrice() {
		return itemPrice;
	}
	public void setItemPrice(double itemPrice) {
		this.itemPrice = itemPrice;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	
}
