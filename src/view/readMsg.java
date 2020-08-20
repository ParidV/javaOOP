package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.message;

public class readMsg {
	message in;
		public readMsg(message in) {
			this.in=in;
		}
		
		public void show() {
			Stage stage = new Stage();
			GridPane grid = new GridPane();
			Label subject = new Label(in.getSubject());
			TextArea tx = new TextArea();
			tx.setEditable(false);
			tx.setMaxSize(300,100);
			tx.setWrapText(true);
			tx.insertText(0, in.getText());
			grid.setAlignment(Pos.CENTER);
			grid.add(subject, 0, 1);
			grid.add(tx, 0, 2);
			grid.setAlignment(Pos.CENTER);
			Scene sc = new Scene(grid,320,200);
			grid.setHgap(15);
			grid.setVgap(15);
			Button forward = new Button("Forward Message",new ImageView(new Image("images/forward-message.png")));
			forward.setOnAction(new EventHandler<ActionEvent>(){
				public void handle(ActionEvent e) {
					new forwardSMS(in).show(stage);
				}
			});
			Button reply = new Button("Reply",new ImageView(new Image("images/reply.png")));
			ToolBar tool = new ToolBar();
			tool.getItems().addAll(forward, new Separator(), reply);
			grid.add(tool, 0, 3);
			reply.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent e) {
					new replySMS(in).show(stage);
				}
			});
			stage.setScene(sc);
			stage.getIcons().add(new Image("images/mail-envelope-open-back-shape.png"));
			stage.setTitle("Message from " + in.getSender());
			sc.getStylesheets().add("images/style.css");
			stage.show();
			
		}
}
