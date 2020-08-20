package view;

import java.io.IOException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.Separator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToolBar;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.bill;
import model.person;
import model.product;
import model.role;
import model.soldItem;
import model.readwrite.ProductRW;

public class PharmacistView {
	
	person ph;
	private static ArrayList<soldItem> cart = new ArrayList<>(); 
	public ArrayList<soldItem> getCart() {
		return cart;
	}
	@SuppressWarnings("static-access")
	public void setCart(ArrayList<soldItem> cart) {
		this.cart = cart;
	}
	public PharmacistView(person ph){
		this.ph = ph;
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void show(Stage stage) {
		
		//menu bar
		BorderPane screen = new BorderPane();
		MenuBar mb = new MenuBar();
		Menu mfile = new Menu("File");
		Menu medit = new Menu("Manage");
		Menu mhelp = new Menu("Help");
		Menu mode = new Menu("Mode");
		MenuItem messages = new MenuItem("Inbox");
		messages.setOnAction(e->{
			new inboxView(ph).show();
		});
		MenuItem lgt = new MenuItem("Log out");
		MenuItem exit = new MenuItem("Exit");
		mfile.getItems().addAll(lgt,exit);
		medit.getItems().add(messages);
		exit.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				stage.close();
			}});
		lgt.setOnAction(e->{
			new Login().show(stage);
		});		
		MenuItem adminmode = new RadioMenuItem("Administrator");
		MenuItem managermode = new RadioMenuItem("Manager");
		mode.setStyle("-fx-background-color: turquoise;");
		mode.getItems().addAll(adminmode,managermode);
		mb.getMenus().addAll(mfile,medit,mhelp);
		if(ph.getType()==role.Administrator) mb.getMenus().add(mode);
		
		adminmode.setOnAction(e-> {
			new AdministratorView(ph).show(stage);
		});
		managermode.setOnAction(e->{
			new ManagerView(ph).show(stage);
		});
		
		screen.setTop(mb);
		
		//tableview
		
		TableView<soldItem> toSell = new TableView();
		TableColumn itemColumn = new TableColumn("Item");
		TableColumn priceColumn = new TableColumn("Price");
		TableColumn quantityColumn = new TableColumn("Quantity");
		TableColumn costColumn = new TableColumn("Cost");
		toSell.getColumns().addAll(itemColumn,priceColumn,quantityColumn,costColumn);
		itemColumn.setCellValueFactory(
                new PropertyValueFactory<soldItem, String>("itemName"));
		priceColumn.setCellValueFactory(
                new PropertyValueFactory<soldItem, String>("itemPrice"));
		quantityColumn.setCellValueFactory(
                new PropertyValueFactory<product, Integer>("quantity"));
		costColumn.setCellValueFactory(
                new PropertyValueFactory<product, Double>("totalPrice"));
		for(soldItem x:cart) {
			toSell.getItems().add(x);
		}
		screen.setCenter(toSell);
		itemColumn.setMinWidth(165);
		itemColumn.setResizable(false);
		priceColumn.setMinWidth(120);
		priceColumn.setResizable(false);
		quantityColumn.setMinWidth(120);
		quantityColumn.setResizable(false);
		costColumn.setMinWidth(120);
		costColumn.setResizable(false);
		//toolbar
		ToolBar tools = new ToolBar();
		Button cartAdd = new Button("Add to Cart",new ImageView(new Image("images/cart.png")));
		Button cartDrop = new Button("Drop item", new ImageView(new Image("images/drop.png")));
		Button cartClear = new Button("Clear Cart", new ImageView(new Image("images/cartclear.png")));
		Button checkOut = new Button("Checkout", new ImageView(new Image("images/receipt.png")));
		
		Button logOut = new Button("Log Out", new ImageView(new Image("images/logout.png")));
		Button inbox= new Button("Inbox" , new ImageView(new Image("images/close-envelope.png")));	
		
		inbox.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				new inboxView(ph).show();
			}
		});
		cartDrop.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {
				cart.remove(toSell.getSelectionModel().getSelectedItem());
				toSell.getItems().clear();
				for(soldItem x: cart) {
					toSell.getItems().add(x);
				}}			
		});
		
		 tools.getItems().addAll(cartAdd,new Separator(),cartDrop,new Separator(),cartClear,new Separator(),checkOut,new Separator(),inbox,new Separator(),logOut);
		screen.setBottom(tools);
		
		// show scene
		Scene sc = new Scene(screen,640,320);
		sc.getStylesheets().add("images/style.css");
		stage.setScene(sc);
		stage.show();
		//button actions
		cartAdd.setOnAction(e->{
			new buyProd(ph).show(stage);
		});
		logOut.setOnAction(e-> new Login().show(stage));
		
		cartClear.setOnAction(e->{
			toSell.getItems().clear();
		}
		);
		ProductRW data = new ProductRW();
		checkOut.setOnAction(e-> {
			if(cart.isEmpty()) {
				new Warning("Please add items in the cart","");
			}
			else
			try {
				for(soldItem r:cart) {
					data.getProd().get(r.getArrindex()).setStock(data.getProd().get(r.getArrindex()).getStock()-r.getQuantity());
					data.writeProd();
				}
				bill r = new bill(cart,ph);
				r.printBill();
				new Warning("Checkout successful! Bill no "+r.billno + " printed.","");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			cart.clear();
			toSell.getItems().clear();
		});
		
		stage.setTitle("Pharmacist "+ ph.getUsername());
	}
		
}
