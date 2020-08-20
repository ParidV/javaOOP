package view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.person;
import model.readwrite.PersonRW;

public class changePW {
	person p;
	
	public changePW(person p){
		this.p=p;
		Stage stage2 = new Stage();
		Label old = new Label("Old Password");
		PasswordField oldf= new PasswordField();
		Label n1 = new Label("New Password");
		PasswordField new1 = new PasswordField();
		Label n2 = new Label("Confirm Password:");
		PasswordField new2 = new PasswordField();
		GridPane grid = new GridPane();
		grid.add(old,1,1);		grid.add(oldf,2,1);
		grid.add(n1,1,2);		grid.add(new1,2,2);
		grid.add(n2,1,3);		grid.add(new2,2,3);
		PersonRW file = new PersonRW();
		Button confirm = new Button("Confirm");
		confirm.setOnAction(e->{
			if(oldf.getText().equals(p.getPassword())) {
				if(new1.getText().equals(new2.getText())) {
					int index = file.getEmp().indexOf(p);
					file.getEmp().get(index).setPassword(new1.toString());
					file.writePerson();
				}
			}
			else new Warning("Old password is incorrect","Error");
		});
		grid.add(confirm,2,4);
		Scene sc = new Scene(grid,400,400);
		stage2.setScene(sc);
		stage2.setTitle(p.getName() + "Change password");
		stage2.show();
	}
}
