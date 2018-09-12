package controlers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public final class LoginSceneController {
	
	private LoginSceneController() {}

	
	public static Scene getLoginScene() throws IOException {
		
		Parent root = FXMLLoader.load(Main.getInstance().getClass().getResource("/views/authorization.fxml"));
		Scene scene = new Scene(root, 350, 500);
		
		Text target = (Text) root.lookup("#actiontarget");

		TextField name = (TextField) root.lookup("#email");
		
		TextField password = (TextField) root.lookup("#password");
		name.setPromptText("Name");
		password.setPromptText("Password");
		
		Button button = (Button) root.lookup("#button");
		button.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
                            
                            // TODO register/login user
                            try {
                                target.setFill(Color.FIREBRICK);
                                target.setText("Sign in button pressed");
                                
                                Main.getInstance().getStage().setScene(new MainSceneController().getMainScene());
                            } catch (IOException ex) {
                                Logger.getLogger(LoginSceneController.class.getName()).log(Level.SEVERE, null, ex);
                            }
				
			}
		});
		
		return scene;
	}
}
