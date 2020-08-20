package model;

import java.io.Serializable;
import java.util.Date;

public class supplier implements Serializable{
		/**
	 * 
	 */
	private static final long serialVersionUID = 8718137971017562392L;
		private date signed;
		private date until;
		private String name;
		private person emp;
		private String pers;
		
		public String getPers() {
			return pers;
		}
		public void setPers(String pers) {
			this.pers = pers;
		}
		public supplier(String name, date until,person emp) {
			this.until = until;
			this.name=name;
			this.setEmp(emp);
			signed = new date(new Date());
			pers = emp.getName() + " " + emp.getSurname();
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public date getSigned() {
			return signed;
		}
		
		public void setSigned(date signed) {
			this.signed = signed;
		}
		
		public date getUntil() {
			return until;
		}
		
		public void setUntil(date until) {
			this.until = until;
		}
		@Override
		public String toString() {
			return name;
		}
		public person getEmp() {
			return emp;
		}
		public void setEmp(person emp) {
			this.emp = emp;
		}		
}
