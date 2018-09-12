/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlers;

import static controlers.MainSceneController.contentPane;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author ognev
 */
public class TransactionSceneController implements BaseController {

    private ChoiceBox choiceCategoryBox;
  
    @Override
    public void openCreateScene() {
        try {
            VBox newTransactionPane = FXMLLoader.load(Main.getInstance().getClass().getResource("/views/new_transaction.fxml"));
           // contentPane.getChildren().remove(0);
             contentPane.getChildren().clear();
            AnchorPane ap = (AnchorPane) newTransactionPane.getChildren().get(0);
            Button save = (Button) ap.lookup("#save");
            
            save.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        Main.getInstance().getStage().setScene(MainSceneController.getInstance().getMainScene());
                    } catch (IOException ex) {
                        Logger.getLogger(TransactionSceneController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            choiceCategoryBox = (ChoiceBox) ap.lookup("#categoryDropDown");
            choiceCategoryBox.setItems(FXCollections.observableArrayList(
                    "Restaurants", "Amazon",
                    "Car Service", "Fast Food")
            );
            contentPane.getChildren().add(newTransactionPane);
        } catch (IOException ex) {
            Logger.getLogger(TransactionSceneController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void openListScene() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
