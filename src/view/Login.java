package view;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import model.person;
import model.role;
import model.readwrite.PersonRW;

public class Login{
	public Login() {}
	
    public void show(Stage stg) {
    	stg.setTitle("Log In");
    	BorderPane bp = new BorderPane(); 
		Label u = new Label("Username: ");
		Label p = new Label ("Password:	 ");
		TextField un = new TextField ();
		PasswordField pw = new PasswordField ();
		GridPane grid = new GridPane();
		ImageView logo = new ImageView(new Image("images/logoph.png"));
		grid.setAlignment(Pos.CENTER);	
		 						grid.add(logo,1,0);
		grid.add(u,0,1);		grid.add(un,1,1);
		grid.add(p,0,2);		grid.add(pw,1,2);
		grid.setVgap(15);
		bp.setCenter(grid);
		bp.setId("pane");
		un.setMaxSize(150, 25);
		pw.setMaxSize(150,25);
		ImageView imageLogin = new ImageView(new Image("images/log-in.png"));
		ImageView imageCancel = new ImageView(new Image("images/cancel.png"));
		Button log = new Button("Log in",imageLogin);
		Button cancel = new Button ("Cancel",imageCancel);
		HBox buttons = new HBox();
		buttons.getChildren().addAll(log,cancel);
		grid.add(buttons,1,3);
		cancel.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent arg0) {
				System.exit(0);				
			}
		});
		
		un.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if(event.getCode().equals(KeyCode.ENTER)) {
					log.getOnAction().handle(new ActionEvent());
				}	
			}});
		pw.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if(event.getCode().equals(KeyCode.ENTER)) {
					
					log.getOnAction().handle(new ActionEvent());
				}	
			}});
		log.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent arg0) {
				PersonRW us=new PersonRW();
				person emp=us.checkUser(un.getText(), pw.getText());
				if(emp==null){
					new Warning("Invalid Login.","Error");
					pw.setText("");
				} else {
					if(emp.getType().equals(role.Pharmacist)){
						(new PharmacistView(emp)).show(stg);
					} else if(emp.getType().equals(role.Administrator)){
						(new AdministratorView(emp)).show(stg);
					}
					else if(emp.getType().equals(role.Manager)){
						(new ManagerView(emp)).show(stg);
					}}}});
		Button forgot = new Button("Forgot Password",new ImageView(new Image("images/padlock.png")));
		forgot.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent h) {
				new forgotPass().show(stg);
			}
		});	
		grid.add(forgot, 1, 4);
		StackPane sp = new StackPane();
		sp.getChildren().add(bp);
      Scene sc= new Scene (sp,600,350);
      sc.getStylesheets().add("images/style.css");
		stg.setScene(sc);
		stg.show();
}}