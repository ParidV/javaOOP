package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Warning {

		public Warning(String message, String title) {
			Stage st2=new Stage();
			Text tx=new Text(message);
			tx.setStyle("-fx-font-size: 14px;");
			BorderPane sp=new BorderPane();
			StackPane sppp = new StackPane();
			sp.setTop(sppp);
			sppp.getChildren().add(tx);
			Button ok = new Button("Okay");
			ok.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent arg0) {
					st2.close();
				}});
			ok.setStyle("-fx-background-color: \r\n" + 
					"							  #c3c4c4,\r\n" + 
					"							  linear-gradient(#d6d6d6 50%, white 100%),\r\n" + 
					"							  radial-gradient(center 50% -40%, radius 200%, #e6e6e6 45%, rgba(230,230,230,0) 50%);\r\n" + 
					"		-fx-background-radius: 30;\r\n" + 
					"		-fx-background-insets: 0,1,1;\r\n" + 
					"		-fx-text-fill: black;\r\n" + 
					"		-fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 3, 0.0 , 0 , 1 );");
			ok.setAlignment(Pos.CENTER);
			sp.setCenter(ok);Scene sc=new Scene(sp,400,80);
			st2.setScene(sc);
			st2.getIcons().add(new Image("images/black-back-closed-envelope-shape.png"));
			st2.setTitle(title);
			sc.setOnKeyPressed(new EventHandler<KeyEvent>() {				
				@Override
				public void handle(KeyEvent event) {
					st2.close();
				}});
			st2.show();
		}	
}
