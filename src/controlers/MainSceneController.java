package controlers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import models.Category;
import models.User;
import models.Wallet;

public class MainSceneController implements Initializable {

    @FXML
    static AnchorPane contentPane;
    @FXML
    private static ListView listView;
    private List<String> stringCategorySet;
    ObservableList observableCategoryList;
    private static MainSceneController instance;
    private static ChoiceBox choiceWalletBox;
    private static Category selectedCategory;
    private static Wallet selectedWallet;

    public Scene getMainScene() throws IOException {
        Parent root = FXMLLoader.load(Main.getInstance().getClass().getResource("/views/main.fxml"));
        Scene scene = new Scene(root, 350, 500);
        contentPane = (AnchorPane) root.lookup("#contentPane");

        listView = (ListView) root.lookup("#listView");
        choiceWalletBox = (ChoiceBox) contentPane.lookup("#walletDropDown");
        List<Wallet> wallets = Main.getUser().getWallets();
        String[] strWallets = new String[wallets.size()];
        for (int i = 0; i < wallets.size(); i++) {
            strWallets[i] = wallets.get(i).getName() + ": " + wallets.get(i).getBalance();
        }
        choiceWalletBox.setItems(FXCollections.observableArrayList(strWallets));

        choiceWalletBox.getSelectionModel().selectFirst();
        if (wallets.size() > 0) {
            loadListView(listView, wallets.get(0));
        }

        choiceWalletBox.getSelectionModel().selectedIndexProperty()
                .addListener(new ChangeListener<Number>() {
                    @Override
                    public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                        System.out.println("clicked on " + oldValue + " " + newValue);
                        loadListView(listView, wallets.get(newValue.intValue()));
                    }
                });

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/main.fxml"));
        fxmlLoader.setController(this);

        return scene;

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        loadListView(listView);
        instance = this;

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
    private void onDashboardClicked() throws IOException {
        Main.getInstance().getStage().setScene(getMainScene());
    }

    @FXML
    private void onLogoutClicked() throws IOException {
        Main.setUser(null);
        Main.getInstance().getStage().setScene(LoginSceneController.getLoginScene());
    }

    @FXML
    private void onNewCategoryClicked(ActionEvent event) {
        CategorySceneController csc = new CategorySceneController();
        csc.openCreateScene();
    }

    @FXML
    private void onOpenCategoriesClicked(ActionEvent event) {
        CategorySceneController csc = new CategorySceneController();
        csc.openListScene();
    }

    @FXML
    private void onNewTransactionClicked(ActionEvent event) throws IOException {
        TransactionSceneController tc = new TransactionSceneController();
        tc.openCreateScene();
    }

    public static int getRandomBetweenRange(int min, int max) {
        int x = (int) ((Math.random() * ((max - min) + 1)) + min);
        return x;
    }

    public void loadListView(ListView listView, Wallet wallet) {
        // TODO from DB

        User user = Main.getUser();

        stringCategorySet = new ArrayList<String>();
//        List<String> names = new ArrayList<String>();
        observableCategoryList = FXCollections.observableArrayList();
        List<Category> categories = Category.getWalletCategories(Main.getUser(), wallet);

        for (int i = 0; i < categories.size(); i++) {
//            names.add(category.getName());
            stringCategorySet.add(categories.get(i).getName() + ": $" + categories.get(i).getTotalAmount() + " (" + categories.get(i).getTCount() + ")");
        }

        observableCategoryList.setAll(stringCategorySet);
        listView.setItems(observableCategoryList);
        listView.setCellFactory(new Callback<ListView<String>, javafx.scene.control.ListCell<String>>() {
            @Override
            public ListCell<String> call(ListView<String> listView) {
                ListViewCells cl = new ListViewCells();

//              cl.setOnMouseClicked(new EventHandler<MouseEvent>() {
//                  @Override
//                  public void handle(MouseEvent event) {
//                     
//                  }
//              });
                return cl;
            }
        });

        listView.getSelectionModel().selectedItemProperty()
                .addListener(new ChangeListener() {
                    @Override
                    public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                        Category category = categories.get(listView.getSelectionModel().getSelectedIndex());
                        setSelectedCategory(category);
                        setSelectedWallet(wallet);
                        TransactionSceneController tc = new TransactionSceneController();
                        tc.openListScene();
                        System.out.println("clicked on " + listView.getSelectionModel().getSelectedIndex()+  " "+ category.getName() + ", wallet_id: " + wallet.getId() + ", category_id: " + category.getId());

                    }
                });

   
    }

    public static MainSceneController getInstance() {
        return instance;
    }

    public static void setSelectedCategory(Category category) {
        MainSceneController.selectedCategory = category;
    }

    public static Category getSelectedCategory() {
        return selectedCategory;
    }

    public static void setSelectedWallet(Wallet wallet) {
        MainSceneController.selectedWallet = wallet;
    }

    public static Wallet getSelectedWallet() {
        return selectedWallet;
    }

}
