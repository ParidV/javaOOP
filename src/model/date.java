package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

public class date implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6789642196438444019L;
	int dd;
	int mm;
	int yyyy;
	
	@SuppressWarnings("deprecation")
	public date(Date z) {
		dd=z.getDate();
		mm=z.getMonth()+1;
		yyyy= z.getYear()+1900;
	}
	
	public date(LocalDate z) {
		dd = z.getDayOfMonth();
		mm = z.getMonthValue();
		yyyy= z.getYear();
	}
	public date(int dd, int mm, int yyyy) {
		this.dd = dd;
		this.mm = mm;
		this.yyyy = yyyy;
	}
	@Override
	public String toString() {
		return dd+"/"+mm+"/"+yyyy;
	}
	
}
