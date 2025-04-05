package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import view.ViewMenu;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception {
		new ViewMenu(primaryStage);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
