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
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.date;
import model.person;
import model.role;
import model.readwrite.PersonRW;

public class EditUser {
	person adm;
	int ind;
	PersonRW ff = new PersonRW();
	EditUser(person r,int selin ){
		adm=r;
		ind=selin;
	}
	public void show(Stage stage) {
	
		PersonRW file = new PersonRW();
		
		
				//labels
				Text msg = new Text("Edit User: ");
				msg.setStyle("-fx-font-size: 16px;");
				Label lname = new Label("Name:");
				Label lsurname = new Label("Surname:");
				Label lusername = new Label("Username:");
				Label lpw = new Label("Password:");
				Label lSalary = new Label("Salary: ");
				Label lphoneno = new Label("Phone no.:");
				Label lemail = new Label("Email");
				Label ldob = new Label("Date of Birth");
				Label position = new Label("Position: ");
				
				//fields
				TextField name = new TextField(file.getEmp().get(ind).getName());
				TextField surname = new TextField(file.getEmp().get(ind).getSurname());
				TextField username = new TextField(file.getEmp().get(ind).getUsername());
				TextField phoneno = new TextField(file.getEmp().get(ind).getPhone());
				TextField email = new TextField(file.getEmp().get(ind).getEmail());
				DatePicker dob = new DatePicker();
				dob.setValue(file.getEmp().get(ind).getDob());
				TextField pw1 = new TextField(file.getEmp().get(ind).getPassword());
				TextField Salary = new TextField(file.getEmp().get(ind).getSalary()+"");
				ComboBox<role> Type = new ComboBox<>();
				Type.setItems(FXCollections.observableArrayList(role.values()));
				Type.setValue(file.getEmp().get(ind).getType());
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
				gp.add(lSalary,0,7);		gp.add(Salary,1,7);
				gp.add(position,0,8);		gp.add(Type,1,8);
				gp.add(lphoneno,0,9);		gp.add(phoneno,1,9);
				gp.add(lemail,0,10);		gp.add(email,1,10);
				gp.add(buttons,1,12);
				
				username.setDisable(true);
				ok.setOnAction(e -> {
					file.getEmp().get(ind).setDob(new date(dob.getValue().getDayOfMonth(),dob.getValue().getMonthValue(),dob.getValue().getYear()));
					file.getEmp().get(ind).setEmail(email.getText());
					file.getEmp().get(ind).setName(name.getText());
					file.getEmp().get(ind).setPassword(pw1.getText());
					file.getEmp().get(ind).setPhone(phoneno.getText());
					file.getEmp().get(ind).setSalary(Double.parseDouble(Salary.getText()));
					file.getEmp().get(ind).setUsername(username.getText());
					file.getEmp().get(ind).setType(Type.getValue());
					file.getEmp().get(ind).setSurname(surname.getText());
					file.writePerson();
					
					new ViewUsers(adm).show(stage);
				});
				back.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						new AdministratorView(adm).show(stage);
					}});
				
				
		Scene sc=new Scene(gp,420,450);
		sc.getStylesheets().add("images/style.css");
		stage.setScene(sc);
		stage.setTitle("Edit: "+file.getEmp().get(ind).getUsername());
		
	}

}
