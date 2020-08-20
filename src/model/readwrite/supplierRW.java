package model.readwrite;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import model.supplier;

public class supplierRW {
	private File fu;
	private ArrayList<supplier> sup;
	public supplierRW(){
		fu=new File("contracts.ser");
		sup=new ArrayList<supplier>();
		if(!fu.exists()){
			writeSup();
		} else {
			
			sup=readSup();
		}
	}
	
	public ArrayList<supplier> getSup() {
		return sup;
	}

	@SuppressWarnings("unchecked")
	public ArrayList<supplier> readSup() {
		try {
			FileInputStream fis=new FileInputStream(fu);
			ObjectInputStream ois=new ObjectInputStream(fis);
			sup=(ArrayList<supplier>) ois.readObject();
			ois.close();
		} catch (FileNotFoundException e) {
			System.err.println("File not Found!!!");
		} catch (ClassNotFoundException e) {
			System.err.println("Class not Found!!!");
		} catch (IOException e) {
			System.err.println("File not accessable!!!");
		}
		return sup;
	}
	
	public void writeSup() {
		try {
			FileOutputStream fos = new FileOutputStream(fu);
			ObjectOutputStream oos=new ObjectOutputStream(fos);
			oos.writeObject(sup);
			oos.close();
			fos.close();
		} catch (FileNotFoundException e) {
			System.err.println("File not Found!!!");
		} catch (IOException e) {
			System.err.println("File not Writable!!!");
		}
		readSup();
	}
	
	public void addSup(supplier e){
		sup.add(e);
		writeSup();
	}
	public void removeSup(supplier e){
		sup.remove(e);
		writeSup();
	}
}
