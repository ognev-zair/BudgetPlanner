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
import models.User;

public class Main extends Application {

    private Stage primaryStage;
    private static Main instance;
    private static User user;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        this.instance = this;
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Budget Planner");

        if (user == null) {
            primaryStage.setScene(LoginSceneController.getLoginScene());
        } else {
            primaryStage.setScene(new MainSceneController().getMainScene());
        }
        
        primaryStage.show();
    }

    public Stage getStage() {
        return primaryStage;
    }

    public static Main getInstance() {
        return instance;
    }

    public static void setUser(User user) {
        Main.user = user;
    }

    public static User getUser() {
        return user;
    }
}
