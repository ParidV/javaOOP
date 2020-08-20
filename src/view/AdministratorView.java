package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.person;
import model.product;
import model.role;
import model.readwrite.PersonRW;
import model.readwrite.ProductRW;

public class AdministratorView {
	person ad;
	public AdministratorView(person ad) {
		this.ad=ad;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void show(Stage stage) {
		MenuBar mb = new MenuBar();
		Menu m1 = new Menu("File");
		Menu m2 = new Menu("Manage");
		Menu mode = new Menu("Mode");
		Menu m3 = new Menu("Help");
		mb.getMenus().addAll(m1,m2,m3);
		MenuItem exit = new MenuItem("Exit");
		MenuItem about = new MenuItem("About");
		MenuItem pharmode = new RadioMenuItem("Pharmacist");
		MenuItem managermode = new RadioMenuItem("Manager");
		MenuItem lgt = new MenuItem("Log out");
		Menu m22 = new Menu("Suppliers");
		MenuItem m221 = new MenuItem("Add Supplier");
		MenuItem m222 = new MenuItem("View/Edit Suppliers");
		Menu m23 = new Menu("Products");
		MenuItem m231 = new MenuItem("Add Product");
		MenuItem m232 = new MenuItem("View/Edit Products");
		

		Menu mmm = new Menu("Users");
		MenuItem mm1 = new MenuItem("Add User");
		MenuItem mm2 = new MenuItem("View/Edit Users");
		
		m2.getItems().addAll(m22,m23);
		m2.getItems().add(mmm);
		mmm.getItems().addAll(mm1,mm2);
		m22.getItems().addAll(m221,m222);
		m23.getItems().addAll(m231,m232);
		mm1.setOnAction(e->{
			new RegisterUser(ad).show(stage);
		});
		mm2.setOnAction(e->{
			new ViewUsers(ad).show(stage);
		});
		m221.setOnAction(e->{
			new newSupplier(ad).show();
		});
		m222.setOnAction(e->{
			new viewSuppliers(ad).show(stage);
		});
		m231.setOnAction(e->{
			new AddProduct(ad).show(stage);
		});
		m232.setOnAction(e->{
			new ViewProducts(ad).show(stage);
		});
		lgt.setOnAction(e->{
			new Login().show(stage);
		});
		
		
		mode.setStyle("-fx-background-color: turquoise;");
		mode.getItems().addAll(pharmode,managermode);
		MenuItem messages = new MenuItem("Inbox");
		messages.setOnAction(e->{
			new inboxView(ad).show();
		});
		m2.getItems().add(messages);
		pharmode.setOnAction(e->{
			new PharmacistView(ad).show(stage);
		});
		managermode.setOnAction(e->
			new ManagerView(ad).show(stage));
		m1.getItems().add(lgt);
		m1.getItems().add(exit);
		
		mb.getMenus().add(mode);
		
		Button addp = new Button("Add Product",new ImageView(new Image("images/writing.png")));
		exit.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				stage.close();
			}});
		m3.getItems().add(about);
		about.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				new Warning("Made by Qemal Alliu","About");
			}});
		
		BorderPane screen = new BorderPane();
		GridPane gp=new GridPane();
		
		Button allusers = new Button("View Users",new ImageView(new Image("images/group.png")));
		Button reguser = new Button("Register User",new ImageView(new Image("images/add-user-button.png")));
		Button logout = new Button("Log out",new ImageView(new Image("images/logout.png")));
		Button inventory = new Button("Inventory",new ImageView(new Image("images/list.png")));
		BorderPane inside = new BorderPane();
		screen.setCenter(inside);
		Text welcome = new Text("Welcome "+ad.getName());
		HBox b = new HBox();
		Region r1 = new Region();
		HBox.setHgrow(r1,Priority.ALWAYS);
		b.getChildren().addAll(welcome);
		welcome.setStyle("-fx-font-size: 20;");
		BorderPane.setAlignment(b, Pos.CENTER);
		BorderPane.setMargin(b, new Insets(12,12,12,12)); 
		inside.setTop(b);
		reguser.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				new RegisterUser(ad).show(stage);
			}});
		inventory.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				new ViewProducts(ad).show(stage);
			}});
		allusers.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				new ViewUsers(ad).show(stage);
			}});
		logout.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				new Login().show(stage);
			}});
		addp.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				new AddProduct(ad).show(stage);
			}});
		
		final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final BarChart<String,Number> bc = 
            new BarChart<String,Number>(xAxis,yAxis);
        bc.setTitle("Employee salaries");
        xAxis.setLabel("Employee");       
        yAxis.setLabel("Salary");	
        XYChart.Series series1 = new XYChart.Series();
        series1.setName("Administrator");   
        XYChart.Series series2 = new XYChart.Series();
        XYChart.Series series3 = new XYChart.Series();
        series2.setName("Manager");
        series3.setName("Pharmacist");
        bc.setAnimated(true);
        for(person x:new PersonRW().getEmp()) {
        	if(x.getType()==role.Administrator)
        	  series1.getData().add(new XYChart.Data(x.getName(),x.getSalary()));
        	else if(x.getType()==role.Manager) 
        		series2.getData().add(new XYChart.Data(x.getName(),x.getSalary()));
        		else
        	series3.getData().add(new XYChart.Data(x.getName(),x.getSalary()));
        
        }
        bc.setMaxSize(330,450);
        bc.getData().addAll(series1,series2,series3);
        ObservableList<PieChart.Data> pieChartData= FXCollections.observableArrayList();
        for(product x: new ProductRW().getProd()) {
        	pieChartData.add(new PieChart.Data(x.getName(), x.getStock()));
        }
        final PieChart chart = new PieChart(pieChartData);
        chart.setTitle("Items in stock");
        chart.setMaxSize(330, 450);
		ToolBar tb = new ToolBar();
		Button inbox= new Button("Inbox", new ImageView(new Image("images/close-envelope.png")));		
		tb.getItems().addAll(allusers,new Separator(),reguser,new Separator(),addp,new Separator(),inventory,new Separator(),inbox,new Separator(),logout);
		inbox.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				new inboxView(ad).show();
			}
		});
		inside.setRight(chart);
		gp.add(tb,0,1);
		gp.setAlignment(Pos.CENTER);
		gp.setVgap(10);		
		screen.setTop(mb);
		screen.setBottom(gp);
		inside.setLeft(bc);
		Scene sc=new Scene(screen,670, 530);
		sc.getStylesheets().add("images/style.css");
		stage.setResizable(false);
		stage.setScene(sc);
		stage.setTitle(ad.getName());
	}
	}
