package view;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.Separator;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.administrator;
import model.person;
import model.product;
import model.readwrite.ProductRW;

public class ManagerView {
person mng;
	public ManagerView(person mng) {
		this.mng=mng;
	}
	public void show(Stage stage) {
		ProductRW check = new ProductRW();
		ArrayList<product> pr = check.getProd();
		BorderPane screen = new BorderPane();
		MenuBar mb = new MenuBar();
		Menu mfile = new Menu("File");
		Menu medit = new Menu("Manage");
		Menu mhelp = new Menu("Help");
		Menu mode = new Menu("Mode");
		MenuItem adminmode = new RadioMenuItem("Administrator");
		MenuItem pharmode = new RadioMenuItem("Pharmacist");
		mode.setStyle("-fx-background-color: turquoise;");
		
		Menu m22 = new Menu("Suppliers");
		MenuItem m221 = new MenuItem("Add Supplier");
		MenuItem m222 = new MenuItem("View/Edit Suppliers");
		Menu m23 = new Menu("Products");
		MenuItem m231 = new MenuItem("Add Product");
		MenuItem m232 = new MenuItem("View/Edit Products");
		m22.getItems().addAll(m221,m222);
		m23.getItems().addAll(m231,m232);
		m221.setOnAction(e->{
			new newSupplier(mng).show();
		});
		m222.setOnAction(e->{
			new viewSuppliers(mng).show(stage);
		});
		m231.setOnAction(e->{
			new AddProduct(mng).show(stage);
		});
		m232.setOnAction(e->{
			new ViewProducts(mng).show(stage);
		});
		mode.getItems().addAll(adminmode,pharmode);
		mb.getMenus().addAll(mfile,medit,mhelp);
		medit.getItems().addAll(m22,m23);
		
		
		if(mng instanceof administrator) mb.getMenus().add(mode);
		
		adminmode.setOnAction(e-> {
			new AdministratorView(mng).show(stage);
		});
		pharmode.setOnAction(e->{
			new PharmacistView(mng).show(stage);
		});
		MenuItem lgt = new MenuItem("Log out");
		lgt.setOnAction(e->{
			new Login().show(stage);
		});
		ObservableList<PieChart.Data> pieChartData= FXCollections.observableArrayList();
        for(product x: new ProductRW().getProd()) {
        	pieChartData.add(new PieChart.Data(x.getName(), x.getStock()));
        }
        final PieChart chart = new PieChart(pieChartData);
        chart.setTitle("Items in stock");
        chart.setMaxSize(450, 250);
        screen.setCenter(chart);
		MenuItem exit = new MenuItem("Exit");
		exit.setOnAction(e->{
			stage.close();
		});
		
		mfile.getItems().addAll(lgt,exit);
		
		Button addp = new Button("Add Product",new ImageView(new Image("images/writing.png")));
		Button inventory = new Button("Inventory",new ImageView(new Image("images/list.png")));
		Button logout = new Button("Log out",new ImageView(new Image("images/logout.png")));
		Button contr = new Button("Suppliers",new ImageView(new Image("images/handshake.png")));
		Button inbox= new Button("Inbox", new ImageView(new Image("images/close-envelope.png")));
		inbox.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				new inboxView(mng).show();
			}
		});
		//toolbar
		ToolBar tools = new ToolBar();
		tools.getItems().addAll(inventory,
				new Separator(),
				addp,new Separator(),contr, new Separator(), inbox, new Separator(), logout);
		
		
		//actions
		inventory.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				new ViewProducts(mng).show(stage);
			}});
		addp.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				new AddProduct(mng).show(stage);
			}});
		logout.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				new Login().show(stage);
			}});
		contr.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent rr) {
				new viewSuppliers(mng).show(stage);
			}
		});
		
		screen.setBottom(tools);
		screen.setTop(mb);
	//	screen.setCenter(profit);
		Scene sc = new Scene(screen, 580, 310);
		stage.setTitle("Manager "+mng.getName());
		stage.setScene(sc);
		stage.show();
		sc.getStylesheets().add("images/style.css");
		String warn = "Low quantity of ";
		String in = "Low quantity of ";
		for(product x:pr) {
			if(x.getStock()<5) warn+=x.getName()+" ,";
		}
		if(!(warn.equals(in))) new Warning(warn+" please supply as soon as possible.","Warning!");
		
		stage.setTitle("Manager " + mng.getUsername());
	}
	
}
