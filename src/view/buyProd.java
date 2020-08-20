package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.category;
import model.date;
import model.person;
import model.product;
import model.soldItem;
import model.readwrite.ProductRW;

public class buyProd {
	person seller;
		buyProd(person r){
			seller = r;
		}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	static TableView<product> prod = new TableView();
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void show(Stage stage) {
		prod.getItems().clear();
		TableColumn name = new TableColumn("Name");
		TableColumn gname = new TableColumn("Generic Name");
		TableColumn category = new TableColumn("Category");
		TableColumn stock = new TableColumn("Stock");
		TableColumn supplier = new TableColumn("Supplier");
		TableColumn expiration = new TableColumn("Expiration");
		TableColumn price = new TableColumn("Price");
		prod.getColumns().addAll(name,gname,category,stock,supplier,expiration,price);
		name.setCellValueFactory(
                new PropertyValueFactory<product, String>("name"));
		gname.setCellValueFactory(
                new PropertyValueFactory<product, String>("gname"));
		category.setCellValueFactory(
                new PropertyValueFactory<product, category>("ct"));
		stock.setCellValueFactory(
                new PropertyValueFactory<product, Integer>("stock"));
		supplier.setCellValueFactory(
                new PropertyValueFactory<product, String>("supplier"));
		expiration.setCellValueFactory(
                new PropertyValueFactory<product, date>("expiration"));
		price.setCellValueFactory(
				new PropertyValueFactory<product, Double>("price"));
		name.setMinWidth(120);
		name.setResizable(false);
		gname.setMinWidth(120);
		gname.setResizable(false);
		category.setMinWidth(90);
		category.setResizable(false);
		stock.setMinWidth(60);
		stock.setResizable(false);
		supplier.setMinWidth(75);
		supplier.setResizable(false);
		expiration.setMinWidth(75);
		expiration.setResizable(false);
		price.setMinWidth(60);
		price.setResizable(false);
		
		for(product x: new ProductRW().getProd()) {
			prod.getItems().add(x);
		}
		Button add = new Button("Add to Cart",new ImageView(new Image("images/cart.png")));
		Button back = new Button("Back", new ImageView(new Image("images/back-arrow.png")));
		back.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				new PharmacistView(seller).show(stage);
			}
		});
		prod.resize(600, 350);
		BorderPane bp = new BorderPane();
		bp.setCenter(prod);
		Label quan = new Label("Quantity: ");
		Label srch = new Label("Search: ");
		TextField qry = new TextField();
		ToolBar tools = new ToolBar();
		Spinner<Integer> amount = new Spinner<Integer>();
		amount.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 8));
		tools.getItems().addAll(srch, qry, new Separator(),quan, amount, new Separator(), add,new Separator(),back);
		qry.setOnKeyReleased(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent arg0) {
				prod.getItems().clear();
				for(product p: new ProductRW().getProd()) {
					if(p.toString().toLowerCase().contains(qry.getText().toLowerCase()))
				prod.getItems().add(p);
				}}});
		amount.setMaxWidth(70);
		bp.setBottom(tools);
		add.setOnAction(e->{
			product r = prod.getSelectionModel().getSelectedItem();
			if(r.getStock()<amount.getValue()) 
				new Warning("Low quantity on stock","");
			else {
			prod.getSelectionModel().getSelectedItem().sell(amount.getValue());
			new ProductRW().writeProd();
			new PharmacistView(seller).getCart().add(new soldItem(prod.getSelectionModel().getSelectedIndex(),r.getName(), amount.getValue(), r.getPrice()));
			new PharmacistView(seller).show(stage);
			}
		});
		Scene sc = new Scene(bp,656,325);
		sc.getStylesheets().add("images/style.css");
		stage.setScene(sc);
		stage.setTitle("Add to cart");
		stage.show();
	}
	
}
