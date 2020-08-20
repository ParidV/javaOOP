package model;

import java.io.Serializable;
import java.time.LocalDate;

public abstract class person implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5816805953835726320L;
	private String name;
	private String surname;
	private double salary;
	private date dob;
	private String email;
	private String phone;
	private String username;
	private String password;
	private role type;
	private static int id = 1 ;
	private int userID;
	
	public person(String name, String surname, double salary, date dob, String email, String phone, String username,
			String password) {
		this.name = name;
		this.surname = surname;
		this.salary = salary;
		this.dob = dob;
		this.email = email;
		this.phone = phone;
		this.username = username;
		this.password = password;
		userID = id++;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	public LocalDate getDob() {
		return LocalDate.of(dob.yyyy, dob.mm, dob.dd);
	}
	public void setDob(date dob) {
		this.dob = dob;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public role getType() {
		return type;
	}
	public void setType(role type) {
		this.type = type;
	}
	public static int getId() {
		return id;
	}
	public static void setId(int id) {
		person.id = id;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	@Override
	public String toString() {
		return userID+ ". "+name + " | " + surname + " | " + salary + " | " + dob + " | "
				+ email + " | " + phone + " | " + username + " | " + password + " | " + type;
	}
}
