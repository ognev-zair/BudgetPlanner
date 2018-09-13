/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlers;

import com.sun.javafx.charts.ChartLayoutAnimator;
import static controlers.MainSceneController.contentPane;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import models.Category;
import models.Wallet;

/**
 *
 * @author ognev
 */
public class CategorySceneController implements BaseController {
        @FXML
    private ListView listView;
        
    private ChoiceBox choiceBox;
        private Set<String> stringCategorySet = new HashSet<String>();
            ObservableList observableCategoryList = FXCollections.observableArrayList();
    @Override
    public void openCreateScene() {
          try {
            VBox newTransactionPane = FXMLLoader.load(Main.getInstance().getClass().getResource("/views/new_category.fxml"));
            contentPane.getChildren().clear();
            
            AnchorPane ap = (AnchorPane) newTransactionPane.getChildren().get(0);
            choiceBox = (ChoiceBox) ap.lookup("#categoryDropDown");
            TextField name = (TextField) ap.lookup("#name");
            Button save = (Button) ap.lookup("#save");
            save.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    Category.createCategory(name.getText(), choiceBox
                            .getSelectionModel()
                            .getSelectedItem().toString(), Main.getUser());
                    openListScene();
                }
            });
            
            choiceBox.setItems(FXCollections.observableArrayList(
                    Category.TYPE_INCOME, Category.TYPE_EXPENCE)
            );
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
                addWallet.setText("Add category");
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
        stringCategorySet = new HashSet<String>();
        observableCategoryList = FXCollections.observableArrayList();
        List<Category> categories = Category.getUserCategories(Main.getUser());

        for (Category category : categories) {
            stringCategorySet.add(category.getName()+ ": " + category.getType());
        }

        observableCategoryList.setAll(stringCategorySet);
        listView.setItems(observableCategoryList);
        listView.setCellFactory(new Callback<ListView<String>, javafx.scene.control.ListCell<String>>() {
            @Override
            public ListCell<String> call(ListView<String> listView) {
                return new ListViewCells();
            }
        });
    }
    
}
