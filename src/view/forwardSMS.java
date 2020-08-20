package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import model.readwrite.messagesRW;

public class forwardSMS {
	message sms;
	
	public forwardSMS(message rec) {
		sms=rec;
	}
	
	public void show(Stage stage) {
		GridPane grid = new GridPane();
		Label recipent = new Label("Recipent: ");
		Label subject = new Label("Subject: ");
		TextField rec = new TextField();
		TextField sbj = new TextField();
		sbj.setText("Fwd: " + sms.getSubject());
		TextArea tx = new TextArea();
		tx.setMaxSize(300,100);
		tx.setWrapText(true);
		tx.insertText(0, sms.getText() +"\n\n Forwarded from: "+sms.getSender());
		grid.add(recipent, 0, 0);		grid.add(rec, 1, 0);
		grid.add(subject, 0, 1);  		grid.add(sbj, 1, 1);
		grid.add(tx, 1, 2);
		grid.setHgap(15);
		grid.setVgap(15);
		Button send = new Button("Send Message",new ImageView(new Image("images/paper-plane.png")));
		messagesRW n = new messagesRW();
		send.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				n.sendSMS(new message(sms.getRecipent(),rec.getText(),sbj.getText(),tx.getText()));
				n.writeSMS();
				stage.close();
				new Warning("Message forwarded successfully","Success");
			}
		}
				);
		grid.add(send, 1,3);
		grid.setAlignment(Pos.CENTER);
		Scene sc = new Scene(grid,400,250);
		stage.setScene(sc);
		sc.getStylesheets().add("images/style.css");
		stage.getIcons().add(new Image("images/forward-message.png"));
		stage.setTitle("Forward " + sms.getSubject());
	}
}
