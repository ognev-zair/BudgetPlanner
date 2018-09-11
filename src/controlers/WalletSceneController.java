/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlers;

import static controlers.MainSceneController.contentPane;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author ognev
 */
public class WalletSceneController implements BaseController {

   // private ChoiceBox choiceCategoryBox;
      private Set<String> stringWalletSet = new HashSet<String>();
          ObservableList observableWalletList = FXCollections.observableArrayList();
    @Override
    public void openCreateScene() {
        try {
            VBox newTransactionPane = FXMLLoader.load(Main.getInstance().getClass().getResource("/views/new_wallet.fxml"));
           // contentPane.getChildren().remove(0);
             contentPane.getChildren().clear();
            AnchorPane ap = (AnchorPane) newTransactionPane.getChildren().get(0);
//            choiceCategoryBox = (ChoiceBox) ap.lookup("#categoryDropDown");
//            choiceCategoryBox.setItems(FXCollections.observableArrayList(
//                    "Restaurants", "Amazon",
//                    "Car Service", "Fast Food")
//            );
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