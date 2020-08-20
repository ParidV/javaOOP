package model;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;


public class bill {
	static int num = 1;
	public int billno;
	private ArrayList<soldItem> item = new ArrayList<soldItem>();
	private person seller;
	
	public bill(ArrayList<soldItem> item,person seller) {
		this.item = item;
		this.seller = seller;
		billno = num++;
	}
	private double toPay() {
		double total=0;
		for(soldItem r:item) {
			total+=r.getTotalPrice();
		}
		return total;
	}
	
	public void printBill() throws IOException {
		while(new File("bills/"+seller.getUsername()+"/"+billno +".txt").exists()) {
			billno++;
		}
		File file = new File("bills/"+seller.getUsername()+"/"+billno +".txt");
		file.getParentFile().mkdirs();
		PrintWriter print = new PrintWriter(file);
		print.println("Bill number: "+billno);
		print.println();
		print.println(new Date());
		print.println();
		print.println("--------------------------------------------------------");
		print.println("Item\t\tPrice\t\tQuantity\tCost");
		print.println("--------------------------------------------------------");
		for(soldItem r:item) {
			print.println(r.getItemName()+"\t"+r.getItemPrice()+"\t\t"+r.getQuantity()+"\t\t"+r.getTotalPrice());
		}
		print.println("----------------------------------------------------\n");
		print.println();
		print.println("Seller: "+ seller.getName()+"			Total to pay: $"+ toPay());
		print.println();
		print.println("\n Thank you for shopping with us");
		print.close();
	}
	
}
