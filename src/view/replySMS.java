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

public class replySMS {
	message in;
	public replySMS(message in) {
this.in = in;
}

	public void show(Stage stage) {
		GridPane grid = new GridPane();
		Label recipent = new Label("Recipent: ");
		Label subject = new Label("Subject: ");
		TextField rec = new TextField(in.getSender());
		rec.setDisable(true);
		TextField sbj = new TextField();
		sbj.setText("Re: " + in.getSubject());
		TextArea tx = new TextArea();
		tx.setMaxSize(300,100);
		tx.setWrapText(true);
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
				n.sendSMS(new message(in.getRecipent(),rec.getText(),sbj.getText(),tx.getText()));
				n.writeSMS();
				stage.close();
				new Warning("Message sent","Success");
			}
		}
				);
		grid.add(send, 1,3);
		grid.setAlignment(Pos.CENTER);
		Scene sc = new Scene(grid,400,250);
		stage.setScene(sc);
	//	stage.getIcons().clear();
		
		sc.getStylesheets().add("images/style.css");
		stage.getIcons().add(new Image("images/reply.png"));
		stage.setTitle("Reply to " + in.getSender());
		stage.show();
	}
		
	}

