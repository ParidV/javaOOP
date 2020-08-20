package view;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.category;
import model.date;
import model.person;
import model.product;
import model.role;
import model.supplier;
import model.readwrite.ProductRW;

public class ViewProducts {
	person r;
	ViewProducts(person r){
		this.r=r;
	}
	private TableView<product> prod = new TableView<>();
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void show(Stage stage) {
		ProductRW fil = new ProductRW();
		ArrayList<product> pr = fil.getProd();
		TableColumn name = new TableColumn("Name");
		TableColumn gname = new TableColumn("Generic Name");
		TableColumn purchased = new TableColumn("Purchased Date");
		TableColumn category = new TableColumn("Category");
		TableColumn stock = new TableColumn("Stock");
		TableColumn supplier = new TableColumn("Supplier");
		TableColumn expiration = new TableColumn("Expiration");
		TableColumn price = new TableColumn("Price");
		prod.getColumns().addAll(name,gname,purchased,category,stock,supplier,expiration,price);
		name.setCellValueFactory(
                new PropertyValueFactory<product, String>("name"));
		gname.setCellValueFactory(
                new PropertyValueFactory<product, String>("gname"));
		purchased.setCellValueFactory(
                new PropertyValueFactory<product, date>("purchased"));
		category.setCellValueFactory(
                new PropertyValueFactory<product, category>("ct"));
		stock.setCellValueFactory(
                new PropertyValueFactory<product, Integer>("stock"));
		supplier.setCellValueFactory(
                new PropertyValueFactory<product, supplier>("supplier"));
		expiration.setCellValueFactory(
                new PropertyValueFactory<product, date>("expiration"));
		price.setCellValueFactory(
				new PropertyValueFactory<product, Double>("price"));
		prod.setMaxSize(750, 350);
		Button del = new Button("Delete",new ImageView(new Image("images/rubbish-bin.png")));
		del.setOnAction(e -> {
			for(product x: prod.getSelectionModel().getSelectedItems()) {
				fil.removeProd(x);
				new ViewProducts(r).show(stage);
				}});
		for(product x: pr) {
			prod.getItems().add(x);
		}
		TextField srchbx= new TextField();
		srchbx.setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent e) {
				prod.getItems().clear();
				for(product p: new ProductRW().getProd()) {
					if(p.toString().toLowerCase().contains(srchbx.getText().toLowerCase()))
				prod.getItems().add(p);
			}
			}	});
		BorderPane bp = new BorderPane();
		bp.setCenter(prod);
		BorderPane screen = new BorderPane();
		Scene sc = new Scene(screen,750,400);
		TextArea ta= new TextArea();
		ta.setPromptText("Description will appear here...");
		ta.setMaxSize(400, 100);
		ta.setEditable(false);
		ta.setWrapText(true);
		prod.setOnMouseClicked(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent event){
				try {
				ta.clear();
				ta.insertText(0, prod.getSelectionModel().getSelectedItem().getDescription());
				}
				catch(java.lang.NullPointerException e) {}
				}});
	bp.setBottom(ta);
	HBox bot = new HBox();
	ta.setMaxHeight(90);
	
	screen.setCenter(bp);
	ToolBar tools = new ToolBar();
	Button back = new Button("Back", new ImageView(new Image("images/back-arrow.png")));
	tools.getItems().addAll(del,new Separator(),back);
	Button add = new Button("Add to Stock",new ImageView(new Image("images/add.png")));
	VBox left = new VBox();
	Spinner<Integer> adts = new Spinner<>();
	adts.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100));
	tools.getItems().addAll(new Separator(), add);
	left.getChildren().addAll(new HBox(new Text("Search: \t\t"), srchbx),new HBox(new Text("Add to Stock:\t"),adts), tools);
	Region rg = new Region();
	VBox.setVgrow(rg, Priority.ALWAYS);
	HBox.setHgrow(rg, Priority.ALWAYS);
	bot.getChildren().addAll(left,rg, ta);
	tools.resize(280, 40);
	Button addp = new Button("Add Product",new ImageView(new Image("images/writing.png")));
	addp.setOnAction(new EventHandler<ActionEvent>() {
		@Override
		public void handle(ActionEvent event) {
			new AddProduct(r).show(stage);
		}});
	
	add.setOnAction(new EventHandler<ActionEvent>() {
		public void handle(ActionEvent a) {
			if(prod.getSelectionModel().isEmpty()) {
				new Warning("Please choose an item","");
			}
			else {
			fil.getProd().get(prod.getSelectionModel().getSelectedIndex()).setStock(prod.getSelectionModel().getSelectedItem().getStock()+adts.getValue());
			fil.writeProd();
			prod.getItems().clear();
			for(product x: fil.getProd()) 
				prod.getItems().add(x);
			}
			}
			
		}
	);
	
	
	back.setOnAction(e->{
	if(r.getType()==role.Administrator)
			new AdministratorView(r).show(stage);
	else if (r.getType()==role.Manager)
			new ManagerView(r).show(stage);
	else	new PharmacistView(r).show(stage);
	});
	screen.setBottom(bot);
	sc.getStylesheets().add("images/style.css");
		stage.setScene(sc);
		stage.setTitle("View products");
	}
}
