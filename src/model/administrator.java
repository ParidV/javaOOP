package model;

public class administrator extends person {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5953804919068429940L;
	public administrator(String name, String surname, double salary, date dob, String email, String phone,
			String username, String password) {
		super(name, surname, salary, dob, email, phone, username, password);
		setType(role.Administrator);
	}
	@Override
	public String toString() {
		return "administrator :" + super.toString();
	}	
}
