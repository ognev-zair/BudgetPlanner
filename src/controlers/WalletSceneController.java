/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlers;

import static controlers.MainSceneController.contentPane;
import java.io.IOException;
import java.net.URL;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

/**
 *
 * @author ognev
 */
public class WalletSceneController implements BaseController, Initializable {
    @FXML
    private ListView listView;
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
   
               try {
            VBox vbox = FXMLLoader.load(Main.getInstance().getClass().getResource("/views/wallet_list.fxml"));
           // contentPane.getChildren().remove(0);
             contentPane.getChildren().clear();
            AnchorPane ap = (AnchorPane) vbox.getChildren().get(0);
            this.listView = (ListView) ap.lookup("#list");
//            choiceCategoryBox = (ChoiceBox) ap.lookup("#categoryDropDown");
//            choiceCategoryBox.setItems(FXCollections.observableArrayList(
//                    "Restaurants", "Amazon",
//                    "Car Service", "Fast Food")
//            );
            contentPane.getChildren().add(vbox);
              loadListView();
        } catch (IOException ex) {
            Logger.getLogger(TransactionSceneController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
        public void loadListView() {
        // TODO from DB
        stringWalletSet = new HashSet<String>();
        observableWalletList = FXCollections.observableArrayList();
        stringWalletSet.add("String 1");
        stringWalletSet.add("String 2");
        stringWalletSet.add("String 3");
        stringWalletSet.add("String 4");
        observableWalletList.setAll(stringWalletSet);
        listView.setItems(observableWalletList);
        listView.setCellFactory(new Callback<ListView<String>, javafx.scene.control.ListCell<String>>() {
            @Override
            public ListCell<String> call(ListView<String> listView) {
                return new ListViewCells();
            }
        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
      
        //To change body of generated methods, choose Tools | Templates.
    }

    
}