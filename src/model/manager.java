package model;


public class manager extends person {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8200249881987027097L;

	public manager(String name, String surname, double salary, date dob, String email, String phone, String username,
			String password) {
		super(name, surname, salary, dob, email, phone, username, password);
		setType(role.Manager);
	}

	@Override
	public String toString() {
		return "manager: "+ super.toString();
	}
	

}
