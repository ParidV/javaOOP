package model;

public class pharmacist extends person {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7562583761043192277L;

	public pharmacist(String name, String surname, double salary, date dob, String email, String phone, String username,
			String password) {
		super(name, surname, salary, dob, email, phone, username, password);
		setType(role.Pharmacist);
	}

	@Override
	public String toString() {
		return "pharmacist "+ super.toString();
	}
	

}
