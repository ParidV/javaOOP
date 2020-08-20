package view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToolBar;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.message;
import model.person;
import model.readwrite.messagesRW;

public class inboxView {
		person r;
	inboxView(person r){
		this.r=r;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void show() {
		Stage stage=new Stage();
		messagesRW sms = new messagesRW();
		TableView<message> tab = new TableView<>();
		TableColumn sender = new TableColumn("Sender");
		TableColumn subject = new TableColumn("Subject");
		tab.getColumns().addAll(sender,subject);
		sender.setCellValueFactory(new PropertyValueFactory<message,String>("sender"));
		subject.setCellValueFactory(new PropertyValueFactory<message,String>("subject"));
		sender.setMinWidth(180);
		sender.setResizable(false);
		subject.setMinWidth(180);
		subject.setResizable(false);
		tab.setMaxSize(360, 200);
		for(message ms:sms.getSMS()) {
			if(ms.getRecipent().equals(r.getUsername())) {
						tab.getItems().add(ms);
			}
			}
		ToolBar opt = new ToolBar();
		Button send = new Button("Send Message",new ImageView(new Image("images/paper-plane.png")));
		Button read = new Button("Read",new ImageView(new Image("images/close-envelope.png")));
		Button del = new Button("Delete Message", new ImageView(new Image("images/rubbish-bin.png")));
		
		read.setOnAction(e->{
			try {
			new readMsg(tab.getSelectionModel().getSelectedItem()).show();
			sms.getSMS().get(tab.getSelectionModel().getSelectedIndex()).setRead(true);
			sms.writeSMS();
			}
			catch (java.lang.Exception ex) {}
		});
		send.setOnAction(e->{
			new sendMsg(r).show();
		});
		del.setOnAction(e->{
			try {
			sms.deleteSMS(tab.getSelectionModel().getSelectedItem());
			tab.getItems().clear();
			for(message ms:sms.getSMS()) {
				if(ms.getRecipent().equals(r.getUsername())) {
							tab.getItems().add(ms);
				}
				
			
			}}
			catch (java.lang.Exception ex) {
				
			}
		});
		
		opt.getItems().addAll(read,new Separator(),send,new Separator(),del);
		BorderPane sp = new BorderPane();
		sp.setCenter(opt);
		sp.setTop(tab);
		Scene sc = new Scene(sp,365,250);
		sc.getStylesheets().add("images/style.css");
		stage.setScene(sc);
		stage.setResizable(false);
		stage.getIcons().add(new Image("images/close-envelope.png"));
		stage.setTitle("Inbox");
		stage.show();
	}
	
}
