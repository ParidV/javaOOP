package model.readwrite;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import model.administrator;
import model.date;
import model.person;

public class PersonRW {
	private File fu;
	private ArrayList<person> emp;
	
	public PersonRW(){
		fu=new File("people.ser");
		emp=new ArrayList<person>();
		if(!fu.exists()){
			writePerson();
		} else {
			
			emp=readPerson();
		}
	}
	
	public ArrayList<person> getEmp() {
		return emp;
	}

	@SuppressWarnings("unchecked")
	public ArrayList<person> readPerson() {
		try {
			FileInputStream fis=new FileInputStream(fu);
			ObjectInputStream ois=new ObjectInputStream(fis);
			emp=(ArrayList<person>) ois.readObject();
			ois.close();
		} catch (FileNotFoundException e) {
			System.err.println("File not Found!!!");
		} catch (ClassNotFoundException e) {
			System.err.println("Class not Found!!!");
		} catch (IOException e) {
			System.err.println("File not accessable!!!");
		}
		return emp;
	}
	
	public void writePerson() {
		try {
			FileOutputStream fos = new FileOutputStream(fu);
			ObjectOutputStream oos=new ObjectOutputStream(fos);
			if(emp.isEmpty()){
				emp.add(new administrator("test", "user", 520, new date(3,3,1999), "no email", "069",
						"admin", "admin"));
				writePerson();
			}
			oos.writeObject(emp);
			oos.close();
			fos.close();
		} catch (FileNotFoundException e) {
			System.err.println("File not Found!!!");
		} catch (IOException e) {
			System.err.println("File not Writable!!!");
		}
		readPerson();
	}
	public person checkUser(String username,String password){
		for(person x:emp){
			if(username.equals(x.getUsername()) && x.getPassword().equals(password)){
				return x;
			} 
		}
		return null;
	}
	public person findUser(String username) {
		for (person x:emp)
			if(username.equals(x.getUsername())) return x;
		return null;
	}
	public void addPerson(person e){
		emp.add(e);
		writePerson();
	}
	public void removePerson(person e){
		emp.remove(e);
		writePerson();
	}
	public boolean isUsernameAvailable(String user) {
		for(person x:emp)
			if(user.equals(x.getUsername())) return false;
		return true;
	}
	
}


