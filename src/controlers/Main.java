package controlers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {
	
	

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws IOException {
		primaryStage.setTitle("Budget Planner");
		
		Parent root = FXMLLoader.load(getClass().getResource("/views/main.fxml"));
		Scene scene = new Scene(root, 500, 600);
		
		
	
		primaryStage.setScene(scene);

		Text target = (Text) root.lookup("#actiontarget");

		TextField name = (TextField) root.lookup("#name");
		
		TextField password = (TextField) root.lookup("#password");
		name.setPromptText("Name");
		password.setPromptText("Password");
		
		Button button = (Button) root.lookup("#button");
		button.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				target.setFill(Color.FIREBRICK);
				target.setText("Sign in button pressed");
			}
		});

		primaryStage.show();

	}

}
