package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import model.date;
import model.person;
import model.role;
import model.supplier;
import model.readwrite.supplierRW;

public class viewSuppliers {
	person user;
	viewSuppliers(person p){
		user=p;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void show(Stage stage) {
		supplierRW n = new supplierRW();
		TableView<supplier> tab = new TableView<>();
		
		TableColumn supname = new TableColumn("Supplier");
		TableColumn signed = new TableColumn("Signed on");
		TableColumn per = new TableColumn("Signed by");
		TableColumn due = new TableColumn("Valid Until");
		
		tab.getColumns().addAll(supname,signed,per,due);
		supname.setCellValueFactory(new PropertyValueFactory<supplier,String>("name"));
		per.setCellValueFactory(new PropertyValueFactory<supplier,String>("pers"));
		signed.setCellValueFactory(new PropertyValueFactory<supplier,date>("signed"));
		due.setCellValueFactory(new PropertyValueFactory<supplier,date>("until"));
		tab.setMaxSize(360, 200);
		
		for(supplier r:n.getSup()) {
			
			tab.getItems().add(r);
		}
		ToolBar opt = new ToolBar();
		Button delete = new Button("Delete",new ImageView(new Image("images/rubbish-bin.png")));
		Button addnew = new Button("Add",new ImageView(new Image("images/add.png")));
		Button back = new Button("Back", new ImageView(new Image("images/back-arrow.png")));
		
		delete.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent rr) {
				try {
				n.getSup().remove(tab.getSelectionModel().getSelectedIndex());
				n.writeSup();
				tab.getItems().clear();
				for(supplier x: n.getSup()) {
					tab.getItems().add(x);}}
				catch(java.lang.NullPointerException e) {
					new Warning("Please choose a supplier to delete.","");
				}
			}
		}
		);
		
		back.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent a) {
				if(user.getType()==role.Manager)
					new ManagerView(user).show(stage);
				else if(user.getType()==role.Administrator)
					new AdministratorView(user).show(stage);
			}
		});
		addnew.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent r) {
				new newSupplier(user).show();
			}
		});
		Button ref = new Button("",new ImageView(new Image("images/refresh-page-option.png")));
		ref.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent rrr) {
				new viewSuppliers(user).show(stage);
			}
		});
		
		BorderPane bp = new BorderPane();
		bp.setCenter(tab);
		opt.getItems().addAll(addnew,new Separator(),delete,new Separator(),ref,new Separator(),back);
		bp.setBottom(opt);
		
		Scene sc = new Scene(bp,370,250);
		stage.setScene(sc);
		sc.getStylesheets().add("images/style.css");
		stage.setTitle("View suppliers");
		
	}
}
