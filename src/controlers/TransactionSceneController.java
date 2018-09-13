/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlers;

import static controlers.MainSceneController.contentPane;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import models.Category;
import models.Transaction;
import models.Wallet;

/**
 *
 * @author ognev
 */
public class TransactionSceneController implements BaseController {

    @FXML
    private ListView listView;
    private ChoiceBox choiceWalletBox;
    private ChoiceBox choiceCategoryBox;
    List<Category> categories;
    List<Wallet> wallets;
    ObservableList observableWalletList = FXCollections.observableArrayList();
    ObservableList observableCategorytList = FXCollections.observableArrayList();
    ObservableList observableTransactionList;

    @Override
    public void openCreateScene() {
        try {
            VBox newTransactionPane = FXMLLoader.load(Main.getInstance().getClass().getResource("/views/new_transaction.fxml"));
            // contentPane.getChildren().remove(0);
            contentPane.getChildren().clear();
            AnchorPane ap = (AnchorPane) newTransactionPane.getChildren().get(0);
            Button save = (Button) ap.lookup("#save");
            TextField price = (TextField) ap.lookup("#price");
            TextArea desc = (TextArea) ap.lookup("#description");
            save.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd");
                    double sum = Double.parseDouble(price.getText());

                    Wallet wallet = wallets.get(choiceWalletBox.getSelectionModel().getSelectedIndex());
                    Category category = categories.get(choiceCategoryBox.getSelectionModel().getSelectedIndex());
                    wallet.setBalance(Category.TYPE_EXPENCE.equals(category.getType())
                            ? wallet.getBalance() - sum : wallet.getBalance() + sum);
                    wallet.save();

                    Transaction.createTransaction(
                            category,
                            wallet,
                            sum, simpleDateFormat.format(new Date()), desc.getText());

                    try {
                        Main.getInstance().getStage().setScene(MainSceneController.getInstance().getMainScene());
                    } catch (IOException ex) {
                        Logger.getLogger(TransactionSceneController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });

            choiceCategoryBox = (ChoiceBox) ap.lookup("#categoryDropDown");
            categories = Category.getUserCategories(Main.getUser());

            for (Category category : categories) {
                observableCategorytList.add(category.getName() + ": " + category.getType());
            }

            choiceCategoryBox.setItems(observableCategorytList);

            choiceWalletBox = (ChoiceBox) ap.lookup("#walletDropDown");
            wallets = Wallet.getAccountWallets(Main.getUser());

            for (Wallet wallet : wallets) {
                observableWalletList.add(wallet.getName() + ": " + wallet.getBalance());
            }

            choiceWalletBox.setItems(observableWalletList);

            contentPane.getChildren().add(newTransactionPane);
        } catch (IOException ex) {
            Logger.getLogger(TransactionSceneController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void openListScene() {
       try {
            VBox vbox = FXMLLoader.load(Main.getInstance().getClass().getResource("/views/transactions.fxml"));
            // contentPane.getChildren().remove(0);
            contentPane.getChildren().clear();
            AnchorPane ap = (AnchorPane) vbox.getChildren().get(0);
            this.listView = (ListView) ap.lookup("#list");
            Button addWallet = (Button) ap.lookup("#add");
                
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
        HashSet<String> stringTransactionSet = new HashSet<String>();
        observableTransactionList = FXCollections.observableArrayList();
        List<Transaction> transactions = Transaction.getTransactions(MainSceneController.getSelectedCategory(), 
                MainSceneController.getSelectedWallet());

        for (Transaction transaction : transactions) {
            stringTransactionSet.add(transaction.getCategory().getName() + "\n " + "Amount: $" + transaction.getAmount()
                    + "\n" + transaction.getDescription());
        }

        observableTransactionList.setAll(stringTransactionSet);
        listView.setItems(observableTransactionList);
        listView.setCellFactory(new Callback<ListView<String>, javafx.scene.control.ListCell<String>>() {
            @Override
            public ListCell<String> call(ListView<String> listView) {
                return new ListViewCells();
            }
        });
    }
}
