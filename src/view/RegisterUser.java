package view;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.administrator;
import model.date;
import model.manager;
import model.person;
import model.pharmacist;
import model.role;
import model.readwrite.PersonRW;

public class RegisterUser {
	person p;
	public RegisterUser(person a) {
		p=a;
	}
	
	public void show(Stage stage) {
		
		//labels
		Text msg = new Text("Register a new employee");
		Label lname = new Label("Name:");
		Label lsurname = new Label("Surname:");
		Label lusername = new Label("Username:");
		Label lpw = new Label("Password:");
		Label lpwc = new Label("Confirm Password:");
		Label lSalary = new Label("Salary: ");
		Label lphoneno = new Label("Phone no.:");
		Label lemail = new Label("Email");
		Label ldob = new Label("Date of Birth");
		Label position = new Label("Position: ");
		
		//fields
		TextField name = new TextField();
		TextField surname = new TextField();
		TextField username = new TextField();
		TextField phoneno = new TextField();
		TextField email = new TextField();
		DatePicker dob = new DatePicker();
		PasswordField pw1 = new PasswordField();
		PasswordField pw2 = new PasswordField();
		TextField Salary = new TextField();
		
		
		ComboBox<role> Type = new ComboBox<>();
		Type.setItems(FXCollections.observableArrayList(role.values()));
		
		//buttons
		Region r = new Region();
		HBox buttons = new HBox();
		HBox.setHgrow(r,Priority.ALWAYS);
		Button ok = new Button("Ok",new ImageView(new Image("images/confirm.png")));
		Button back = new Button("Back",new ImageView(new Image("images/back-arrow.png")));
		buttons.getChildren().addAll(ok,r,back);
		
		// grid pane arrangement
		GridPane gp = new GridPane();
		gp.setVgap(10);
		gp.setAlignment(Pos.CENTER);
									gp.add(msg,1,0);
		gp.add(lname,0,1);			gp.add(name,1,1);
		gp.add(lsurname,0,2);		gp.add(surname,1,2);
		gp.add(ldob,0,3);			gp.add(dob,1,3);
		gp.add(lusername,0,4);		gp.add(username,1,4);
		gp.add(lpw,0,5);			gp.add(pw1,1,5);
		gp.add(lpwc,0,6);			gp.add(pw2,1,6);
		gp.add(lSalary,0,7);		gp.add(Salary,1,7);
		gp.add(position,0,8);		gp.add(Type,1,8);
		gp.add(lphoneno,0,9);		gp.add(phoneno,1,9);
		gp.add(lemail,0,10);		gp.add(email,1,10);
		gp.add(buttons,1,12);
		back.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				new AdministratorView(p).show(stage);
			}});
		PersonRW p1 = new PersonRW();
			ok.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					try
					{
						date sdob = new date(dob.getValue().getDayOfMonth(), dob.getValue().getMonthValue(), dob.getValue().getYear());
						String semail = email.getText();
					String sname = name.getText();
					String spass = null;
					if(pw1.getText().equals(pw2.getText()))
					spass = pw1.getText();
					String sphone = phoneno.getText();
					double ssal = Double.parseDouble(Salary.getText());
					String ssur = surname.getText();
					String suser = username.getText();
					if(!(p1.isUsernameAvailable(suser))) new Warning("Sorry, that username is taken","");
					else if (spass.length()<6) new Warning("Password should be at least 6 characters","");
					else if (ssur.length()<4 && sname.length()<4) new Warning("Name and surname should be at least 4 characters","");
					else if(dob.getValue().getYear()>2001) new Warning("The employee should be at least 18 years old","");
					else {
					if(Type.getValue()==role.Administrator) {
						p1.addPerson(new administrator(sname, ssur, ssal, sdob, semail, sphone,
								suser, spass));	
						new RegisterUser(p).show(stage);
						new Warning("Success!","");
					}
					else if(Type.getValue()==role.Manager) {
						p1.addPerson(new manager(sname, ssur, ssal, sdob, semail, sphone,
								suser, spass));
						new RegisterUser(p).show(stage);
						new Warning("Success!","");
					}
					else if(Type.getValue()==role.Pharmacist) {
						p1.addPerson(new pharmacist(sname, ssur, ssal, sdob, semail, sphone,
								suser, spass));
						new RegisterUser(p).show(stage);
						new Warning("Success!","");
					}
					if(Type.getSelectionModel().isEmpty())
						new Warning("Please choose a role for the new user","");
					}
					}
					catch(Exception e) {
						new Warning("Please fill the form correctly","Caution");
					}
					}});
		
	
		gp.setHgap(15);
		
		gp.setId("pane");
		Scene sc=new Scene(gp,420,450);
		
		sc.getStylesheets().add("images/style.css");
		stage.setScene(sc);
		stage.setTitle(p.getName()+ ": Register User");	
	}}