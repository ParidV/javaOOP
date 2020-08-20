package view;

import java.time.LocalDate;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import model.category;
import model.date;
import model.person;
import model.product;
import model.role;
import model.supplier;
import model.readwrite.ProductRW;
import model.readwrite.supplierRW;

public class AddProduct {
	person r;
	AddProduct(person ad){
		r=ad;
	}
	
	public void show(Stage stage) {
		
		Label nm	 = new Label("Name: ");
		Label gnm	= new Label("Generic Name:");
		Label ctg 	= new Label("Category: ");
		Label expdt	 = new Label("Expiration Date:");
		Label supp 	= new Label("Supplier:");
		Label stock	 = new Label("Stock quantity: ");
		Label desc	 = new Label("Description: ");
		TextField tname = new TextField();
		TextField tgname = new TextField();
		DatePicker dExpiration = new DatePicker();
		ComboBox<supplier> sup = new ComboBox<>();
		TextField tstock = new TextField();
		TextArea tdesc = new TextArea();
		TextField tprc = new TextField();
		Label ppr = new Label("Purchase Price: ($)");
		Label prc = new Label("Price: ($)");
		TextField tpur = new TextField();
		ComboBox<category> cat = new ComboBox<>();
		tdesc.setMaxSize(180, 120);
		GridPane grid = new GridPane();
		supplierRW xx = new supplierRW();
		cat.setItems(FXCollections.observableArrayList(category.values()));
		sup.setItems(FXCollections.observableArrayList(xx.getSup()));
		Button ad = new Button("",new ImageView(new Image("images/ad.png")));
		Button ref = new Button("",new ImageView(new Image("images/refresh-page-option.png")));
		HBox su = new HBox();
		su.getChildren().addAll(sup,ad,ref);
		grid.add(nm,1,0);	grid.add(tname,2,0);	grid.add(supp, 3, 0);	grid.add(su,4,0);
		grid.add(gnm,1,1);	grid.add(tgname,2,1);	grid.add(stock, 3,1);	grid.add(tstock,4,1);
		grid.add(ctg,1,2);	grid.add(cat,2,2);		grid.add(expdt,3,2); 	grid.add(dExpiration,4,2);	
		grid.add(prc,1,3);	grid.add(tprc,2,3); 	grid.add(ppr,3,3);		grid.add(tpur,4,3);		
		grid.add(desc,1,4);	grid.add(tdesc,2,4);				
		Scene sc = new Scene(grid,660,330);
		ad.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				new newSupplier(r).show();
			}
		});
		ref.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent rrr) {
				sup.getItems().clear();
				sup.setItems(FXCollections.observableArrayList(new supplierRW().getSup()));
			}
		});
		grid.setAlignment(Pos.CENTER);
		ImageView imageAdd = new ImageView(new Image("images/add.png"));
		ImageView imageCancel = new ImageView(new Image("images/back-arrow.png"));
		Button add = new Button("Add",imageAdd);
		Button cancel = new Button("Cancel",imageCancel);
		HBox btn = new HBox();
		Region r1 = new Region();
		HBox.setHgrow(r1,Priority.ALWAYS);
		btn.getChildren().addAll(add,r1,cancel);
		grid.add(btn,4,5);
		
		cancel.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {
				if(r.getType()==role.Administrator)
					new AdministratorView(r).show(stage);
				if(r.getType()==role.Manager)
					new ManagerView(r).show(stage);
			}});
		
		ProductRW prodrw = new ProductRW();
		add.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
			try	{
				if(sup.getSelectionModel().isEmpty())
					new Warning("Please choose the supplier","");
				else {
				String name = tname.getText();
				String gname = tgname.getText();
				String desc = tdesc.getText();
				int quantity = Integer.parseInt(tstock.getText());
				LocalDate exp = dExpiration.getValue();
				date expiration = new date(exp.getDayOfMonth(),exp.getMonthValue(),exp.getYear());
				double price = Double.parseDouble(tprc.getText());
				product p = new product(name, gname, sup.getValue(), expiration, quantity, desc, Double.parseDouble(tpur.getText()), price, cat.getValue());
				prodrw.addProd(p);
				new AddProduct(r).show(stage);
				new Warning("Product added!","Success");
				}
		}
			catch (java.lang.NumberFormatException e) {
				new Warning("Quantity and prices should be numbers","");
			}
			}});
		
		grid.setHgap(10);
		grid.setVgap(10);
		sc.getStylesheets().add("images/style.css");
		stage.setScene(sc);
		stage.setTitle("Add product");
		stage.show();
		
	}
}
