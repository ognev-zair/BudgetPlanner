/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import java.io.IOException;

public class Data
{
    @FXML
    private HBox hBox;
    @FXML
    private Label label1;
    @FXML
    private Label label2;

    public Data()
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/category_item.fxml"));
        fxmlLoader.setController(this);
        try
        {
            fxmlLoader.load();
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    public void setInfo(String string)
    {
        label1.setText(string);
       // label2.setText(string);
    }

    public HBox getBox()
    {
        return hBox;
    }
}