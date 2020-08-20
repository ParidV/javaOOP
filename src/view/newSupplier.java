package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.date;
import model.person;
import model.supplier;
import model.readwrite.supplierRW;

public class newSupplier {
	person per;
	GridPane grid = new GridPane();
	Scene sc= new Scene (grid, 410,300);
	newSupplier(person per){
		this.per=per;
	}
	public void show(){
		Stage stage = new Stage();
		supplierRW f = new supplierRW();
		Label n1 = new Label("Supplier name: ");
		Label n2 = new Label("Contract due: ");		
		Label n3 = new Label("Signed by: ");
		
		Text t = new Text("Register new supplier contract");
		
		TextField tf1 = new TextField();
		DatePicker tf2 = new DatePicker();
		TextField tf3 = new TextField();
		tf3.setText(per.getName()+" "+per.getSurname());
		tf3.setDisable(true);
		grid.setAlignment(Pos.CENTER);
								grid.add(t, 1, 0);
		grid.add(n1, 0, 1);		grid.add(tf1, 1, 1);
		grid.add(n2, 0, 2); 	grid.add(tf2,1,2);
		grid.add(n3, 0, 3); 	grid.add(tf3, 1, 3);
		
		Button confirm = new Button("Confirm", new ImageView(new Image("images/add.png")));
		
		confirm.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent r) {
				f.getSup().add(new supplier(tf1.getText(),new date(tf2.getValue()),per));
				f.writeSup();
				stage.close();
				new Warning("Supplier Added","Success");
			}
		});
		
		grid.add(confirm,1,4);
		grid.setHgap(15);	grid.setVgap(15);
		
		
		sc.getStylesheets().add("images/style.css");
		stage.setScene(sc);
		stage.show();
		stage.setTitle("Add supplier..");
	}

}
