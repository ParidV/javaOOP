package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.readwrite.PersonRW;

public class passReset {
	int index;
	public passReset(int itt) {
		index=itt;
	}

	public void show(Stage stage) {
		PersonRW file = new PersonRW();
		
		GridPane grid = new GridPane();
		
		PasswordField pw1 = new PasswordField();
		PasswordField pw2 = new PasswordField();
		
		
		Label n1 = new Label("New password: ");
		Label n2 = new Label("Confirm password: ");
		
		Text t = new Text("Enter a new password");
		
		grid.setAlignment(Pos.CENTER);
								grid.add(t, 1, 0);
		grid.add(n1, 0, 1);		grid.add(pw1, 1, 1);
		grid.add(n2, 0, 2); 	grid.add(pw2, 1, 2);
		
		Button confirm = new Button("Confirm", new ImageView(new Image("images/us1.png")));
		
		grid.add(confirm,1,3);
		grid.setHgap(15);	grid.setVgap(15);
		confirm.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				if(pw1.getText().equals(pw2.getText()))
					file.getEmp().get(index).setPassword(pw1.getText());
					file.writePerson();
				new Login().show(stage);
				new Warning("Your password was changed successfully.","Success");
			}
		});
		
		Scene sc = new Scene(grid,360,200);
		stage.setScene(sc);
		stage.setTitle("Reset password of "+ file.getEmp().get(index).getUsername());
		
	}

}
