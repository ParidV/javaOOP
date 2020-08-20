package model.readwrite;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import model.message;
public class messagesRW {
	private File fu;
	private ArrayList<message> sms;
	public messagesRW(){
		fu=new File("messages.ser");
		sms=new ArrayList<message>();
		if(!fu.exists()){
			writeSMS();
		} else {
			
			sms=readSMS();
		}
	}
	
	public ArrayList<message> getSMS() {
		return sms;
	}

	@SuppressWarnings("unchecked")
	public ArrayList<message> readSMS() {
		try {
			FileInputStream fis=new FileInputStream(fu);
			ObjectInputStream ois=new ObjectInputStream(fis);
			sms=(ArrayList<message>) ois.readObject();
			ois.close();
		} catch (FileNotFoundException e) {
			System.err.println("File not Found!!!");
		} catch (ClassNotFoundException e) {
			System.err.println("Class not Found!!!");
		} catch (IOException e) {
			System.err.println("File not accessable!!!");
		}
		return sms;
	}
	
	public void writeSMS() {
		try {
			FileOutputStream fos = new FileOutputStream(fu);
			ObjectOutputStream oos=new ObjectOutputStream(fos);
			oos.writeObject(sms);
			oos.close();
			fos.close();
		} catch (FileNotFoundException e) {
			System.err.println("File not Found!!!");
		} catch (IOException e) {
			System.err.println("File not Writable!!!");
		}
		readSMS();
	}
	public void sendSMS(message e){
		sms.add(e);
		writeSMS();
	}
	public void deleteSMS(message e){
		sms.remove(e);
		writeSMS();
	}
	
}
