import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import view.Login;


public class MainApp extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage st) throws Exception {
		(new Login()).show(st);
		st.getIcons().add(new Image("images/logoph.png"));
		st.show();
	}}
