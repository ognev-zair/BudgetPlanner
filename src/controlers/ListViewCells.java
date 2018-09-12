/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlers;
import javafx.scene.control.ListCell;
import models.Data;

public class ListViewCells extends ListCell<String>
{
    @Override
    public void updateItem(String string, boolean empty)
    {
        super.updateItem(string,empty);
        if(string != null)
        {
            Data data = new Data();
            data.setInfo(string);
            setGraphic(data.getBox());
        }
    }
    
    
    
}