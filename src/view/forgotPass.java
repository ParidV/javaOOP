package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.person;
import model.readwrite.PersonRW;

public class forgotPass {
		
	forgotPass(){
		
	}
	PersonRW r = new PersonRW();

	public void show(Stage stage) {
		
		// labels 
		
		Text tt = new Text("Reset Password");
		tt.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
		
		Label nm = new Label("Name: ");
		Label srnm = new Label("Surname: ");
		Label usrm = new Label ("Username: ");
		Label brthdt = new Label("Date of birth: ");
		Label phno = new Label ("Phone number: ");
		Label eml = new Label("Email: ");
		
		// fields
		
		TextField name = new TextField();
		TextField surname = new TextField();
		TextField username = new TextField();
		DatePicker dob = new DatePicker();
		TextField phone = new TextField();
		TextField email = new TextField();
		
		// Button 
		Button change = new Button("Change Password", new ImageView(new Image("images/us1.png")));
		Button back = new Button("Back",new ImageView(new Image("images/back-arrow.png")));
		
		GridPane gp = new GridPane();
		gp.setHgap(15);		gp.setVgap(15);
									gp.add(tt, 1, 0);
		gp.add(nm,0,1);				gp.add(name, 1, 1);
		gp.add(srnm, 0, 2);			gp.add(surname, 1, 2);
		gp.add(usrm, 0, 3); 		gp.add(username, 1, 3);
		gp.add(brthdt, 0, 4); 		gp.add(dob, 1, 4);
		gp.add(phno, 0, 5); 		gp.add(phone, 1, 5);
		gp.add(eml, 0, 6); 			gp.add(email, 1, 6);
									gp.add(change, 1, 7);
									gp.add(back, 1, 8);
		
		change.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent ev) {
				int itt=-1;
				for(person x: r.getEmp()) {
			if(x.getUsername().equals(username.getText())
					&& x.getName().toLowerCase().equals(name.getText().toLowerCase())
					&& x.getSurname().toLowerCase().equals(surname.getText().toLowerCase())
					&& x.getDob().equals(dob.getValue())
					&& x.getPhone().equals(phone.getText())
					&& x.getEmail().toLowerCase().equals(email.getText()))
				 itt = r.getEmp().indexOf(x);
			}
				if(itt==-1) new Warning("Unfortunately, we could not reset your password.\n"
					+ "Please contact your administrator","Incorrect Information"); 
			else if(r.getEmp().contains((r.getEmp().get(itt))))
					new passReset(itt).show(stage);
			}});
		back.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent r) {
				new Login().show(stage);
			}
		});
		gp.setAlignment(Pos.CENTER);
		Scene sc = new Scene(gp, 410, 430);
		sc.getStylesheets().add("images/style.css");
		stage.setScene(sc);
		stage.setTitle("Forgot Password");
	}
	}







