package model;

import java.io.Serializable;

public class message implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2539833873948072360L;
	private String sender;
	private String recipent;
	private String subject;
	private String text;
	private boolean read;
	
	public message(String sender, String recipent, String subject, String text) {
		this.sender = sender;
		this.recipent = recipent;
		this.subject = subject;
		this.text = text;
		read=false;
	}
	
	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getRecipent() {
		return recipent;
	}
	public void setRecipent(String recipent) {
		this.recipent = recipent;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public boolean isRead() {
		return read;
	}
	public void setRead(boolean read) {
		this.read = read;
	}
	
	
}
