package model;

import model.readwrite.PersonRW;
import model.readwrite.ProductRW;
import model.readwrite.messagesRW;

public class stats {
	public stats() {
			
	}
	ProductRW prods = new ProductRW();
	PersonRW per = new PersonRW();
	messagesRW ms = new messagesRW();
	
	public double getSalariesTotal()
	{
		double salaries=0;
		for(person x:per.getEmp()) {
			salaries+=x.getSalary();
	}
		return salaries;
	}
	
	public double getSupplyTotal() {
		double stotal = 0;
		for(product x: prods.getProd()) {
			stotal+=(x.getSold()+x.getStock())*x.getPprice();
		}
		return stotal;
	}
	
	public double getIncome(){
		double in = 0;
		for(product x: prods.getProd()) {
			in+=x.getSold()*x.getPrice();
		}
		return in;
	}
	
//	public int unread(String username) {
//		int i= 0;
//		for(message x: new messagesRW().getSMS())
//			if(x.getRecipent().equals(username))
//				if(x.isRead()==false)
//				i++;
//		return i;
//	}
	
}
