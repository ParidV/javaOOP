package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import model.person;
import model.readwrite.PersonRW;

public class ViewUsers {
person r;

	public ViewUsers(person ad) {
		r=ad;
	}
	
	private	TableView<person> Users = new TableView<>();

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void show(Stage stage) {
		Button back = new Button("Back",new ImageView(new Image("images/back-arrow.png")));
		back.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				new AdministratorView(r).show(stage);
			}});
		TextField srch = new TextField();
		Label lsrch = new Label("Search: 	");
		HBox search = new HBox();
		Region rg = new Region();
		HBox.setHgrow(rg, Priority.ALWAYS);
		search.getChildren().addAll(lsrch,srch);
		TableColumn name = new TableColumn("Name");
		TableColumn surname = new TableColumn("Surname");
		TableColumn username = new TableColumn("Username");
		TableColumn password = new TableColumn("Password");
		TableColumn type = new TableColumn("Role");
		name.setCellValueFactory(
                new PropertyValueFactory<person, String>("name"));
		surname.setCellValueFactory(
                new PropertyValueFactory<person, String>("surname"));
		username.setCellValueFactory(
                new PropertyValueFactory<person, String>("username"));
		password.setCellValueFactory(
                new PropertyValueFactory<person, String>("password"));
		type.setCellValueFactory(
                new PropertyValueFactory<person, String>("type"));
		TableColumn salary = new TableColumn("Salary");
		salary.setCellValueFactory(
                new PropertyValueFactory<person, String>("salary"));
		Users.getColumns().addAll(name,surname,type,salary,username,password);
		PersonRW ff = new PersonRW();
		for(person p:ff.getEmp()) {
		Users.getItems().add(p);
		}
		Button del = new Button("Delete",new ImageView(new Image("images/rubbish-bin.png")));
		del.setOnAction(e -> {
			for(person x: Users.getSelectionModel().getSelectedItems()) {
				ff.removePerson(x);
				new ViewUsers(r).show(stage);
			}
		});
		srch.setOnKeyReleased(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent arg0) {
				Users.getItems().clear();
				for(person p:ff.getEmp()) {
					if(p.toString().toLowerCase().contains(srch.getText().toLowerCase()))
				Users.getItems().add(p);
				}}});
		BorderPane bp = new BorderPane();
		bp.setTop(search);
		bp.setCenter(Users);
		ToolBar buttons = new ToolBar();
		
//Button remove = new Button("Delete User");
//	remove.setOnAction(new EventHandler<ActionEvent>() {
//			@Override
//			public void handle(ActionEvent event) {
//			
//				ff.removePerson(Users.getSelectionModel().getSelectedItem());
//				new Warning("Delete successful!");
//				Users.getItems().clear();
//				
//					users = ff.getEmp();
//					Users.getItems().add(p);
//				}
//			}});
		
		bp.setBottom(buttons);
		Button edituser = new Button("Edit User",new ImageView(new Image("images/editUser.png")));
		buttons.getItems().addAll(back, new Separator(), edituser,new Separator(),del);
		edituser.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				try {			
					int ind = Users.getSelectionModel().getSelectedIndex();
				 new EditUser(r, ind).show(stage);
				}
				catch (java.lang.NullPointerException e) {
					new Warning ("Please choose a user to edit","");
				}
			}
			
		});
		
		Scene sc = new Scene(bp,450,450);
		sc.getStylesheets().add("images/style.css");
		stage.setScene(sc);
		stage.show();
		stage.setTitle("View users");
		
	}
	
	public person getselectedPerson() {
			return Users.getSelectionModel().getSelectedItem();
		}
}
