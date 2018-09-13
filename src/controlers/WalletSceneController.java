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
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import models.Wallet;

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
            TextField name = (TextField) ap.lookup("#name");
            TextField balance = (TextField) ap.lookup("#balance");
            Button save = (Button) ap.lookup("#save");
            save.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    Wallet.createWallet(name.getText(), Main.getUser(),
                            Double.parseDouble(balance.getText()));
                    openListScene();
                }
            });
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
            Button addWallet = (Button) ap.lookup("#add_wallet");
            addWallet.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    openCreateScene();
                    //To change body of generated methods, choose Tools | Templates.
                }
            });
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
        List<Wallet> wallets = Wallet.getAccountWallets(Main.getUser());

        for (Wallet wallet : wallets) {
            stringWalletSet.add(wallet.getName() + ": " + wallet.getBalance());
        }

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
