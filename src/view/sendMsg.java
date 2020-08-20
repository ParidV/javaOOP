package view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.message;
import model.person;
import model.readwrite.messagesRW;

public class sendMsg {
		person sender;
		messagesRW file = new messagesRW();
	public sendMsg(person n) {
		sender = n;
	}
	
	public void show() {
		Stage stg = new Stage();
		Label rec = new Label("Recipent");
		Label subj = new Label("Subject");
		TextField user = new TextField();
		TextField sbj = new TextField();
		GridPane grid = new GridPane();
		grid.add(rec, 0,1);
		grid.add(user, 1, 1);
		grid.add(subj, 0, 2);
		grid.add(sbj, 1, 2);
		Label msg = new Label("Message: ");
		grid.setHgap(15);
		grid.setVgap(15);
		TextArea text = new TextArea();
		text.setWrapText(true);
		text.resize(320, 150);
		grid.setAlignment(Pos.CENTER);
		grid.add(msg, 0, 3);
		grid.add(text, 1, 3);
		text.setMaxSize(320, 150);
		Button send = new Button("Send Message",new ImageView(new Image("images/paper-plane.png")));
		send.setOnAction( e->{
			message ne = new message(sender.getUsername(), user.getText() ,sbj.getText(),text.getText());
			file.sendSMS(ne);
			file.writeSMS();
			stg.close();
			new Warning("Message sent!","");
		});
		grid.add(send, 1, 4);
		Scene sc = new Scene(grid,460,340);
		stg.getIcons().add(new Image("images/paper-plane.png"));
		stg.setTitle("Send a New Message");
		stg.setScene(sc);
		sc.getStylesheets().add("images/style.css");
		stg.show();
		
	}
}
