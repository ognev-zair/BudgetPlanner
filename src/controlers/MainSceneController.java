package controlers;

import java.io.IOException;
import java.net.URL;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;

public class MainSceneController implements Initializable {

    @FXML
    static AnchorPane contentPane;
    @FXML
    private ListView listView;
    private Set<String> stringCategorySet;
    ObservableList observableCategoryList;

    public Scene getMainScene() throws IOException {
        Parent root = FXMLLoader.load(Main.getInstance().getClass().getResource("/views/main.fxml"));
        Scene scene = new Scene(root, 400, 500);
        contentPane = (AnchorPane) root.lookup("#contentPane");

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/main.fxml"));
        fxmlLoader.setController(this);

        return scene;

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadListView();
    }

    @FXML
    private void onNewWalletClicked(ActionEvent event) throws IOException {
        WalletSceneController wc = new WalletSceneController();
        wc.openCreateScene();
    }

    @FXML
    private void onOpenWalletsClicked(ActionEvent event) {
        WalletSceneController wsc = new WalletSceneController();
        wsc.openListScene();
    }

    @FXML
    private void onWalletShowing() {

    }

    @FXML
    private void onNewCategoryClicked(ActionEvent event) {
        CategorySceneController csc = new CategorySceneController();
        csc.openCreateScene();
    }

    @FXML
    private void onOpenCategoriesClicked(ActionEvent event) {
    }

    @FXML
    private void onNewTransactionClicked(ActionEvent event) throws IOException {
        TransactionSceneController tc = new TransactionSceneController();
        tc.openCreateScene();
    }

    public void loadListView() {
        // TODO from DB
        stringCategorySet = new HashSet<String>();
        observableCategoryList = FXCollections.observableArrayList();
        stringCategorySet.add("String 1");
        stringCategorySet.add("String 2");
        stringCategorySet.add("String 3");
        stringCategorySet.add("String 4");
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
