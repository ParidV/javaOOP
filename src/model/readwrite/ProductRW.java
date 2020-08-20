package model.readwrite;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import model.product;

public class ProductRW {
	private File fu;
	private ArrayList<product> prod;
	public ProductRW(){
		fu=new File("products.ser");
		prod=new ArrayList<product>();
		if(!fu.exists()){
			writeProd();
		} else {
			
			prod=readProd();
		}
	}
	
	public ArrayList<product> getProd() {
		return prod;
	}

	@SuppressWarnings("unchecked")
	public ArrayList<product> readProd() {
		try {
			FileInputStream fis=new FileInputStream(fu);
			ObjectInputStream ois=new ObjectInputStream(fis);
			prod=(ArrayList<product>) ois.readObject();
			ois.close();
		} catch (FileNotFoundException e) {
			System.err.println("File not Found!!!");
		} catch (ClassNotFoundException e) {
			System.err.println("Class not Found!!!");
		} catch (IOException e) {
			System.err.println("File not accessable!!!");
		}
		return prod;
	}
	
	public void writeProd() {
		try {
			FileOutputStream fos = new FileOutputStream(fu);
			ObjectOutputStream oos=new ObjectOutputStream(fos);
			oos.writeObject(prod);
			oos.close();
			fos.close();
		} catch (FileNotFoundException e) {
			System.err.println("File not Found!!!");
		} catch (IOException e) {
			System.err.println("File not Writable!!!");
		}
		readProd();
	}
	
	public void addProd(product e){
		prod.add(e);
		writeProd();
	}
	public void removeProd(product e){
		prod.remove(e);
		writeProd();
	}
	
}
