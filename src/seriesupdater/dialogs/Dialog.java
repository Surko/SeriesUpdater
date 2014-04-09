/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package seriesupdater.dialogs;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import seriesupdater.SeriesUpdater;

/**
 *
 * @author kirrie
 */
public class Dialog {
    
    public static final int AFFIRMATIVE = 0, DECLINED = 1, CANCEL = 2;
    
    public static int answer = -1;
    
    public static class CloseHandler implements EventHandler<ActionEvent> {

        Stage stage;
        
        public CloseHandler(Stage stage) {
            this.stage = stage;
        }
        
        @Override
        public void handle(ActionEvent t) { 
            answer = AFFIRMATIVE;
            stage.close();
        }
        
    }
    
    public static int showInfoDialog(String title, String info, String label, Image img, String btnText) {
        answer = -1;
        
        Stage stage = new Stage();
        stage.setTitle(title);
        stage.setResizable(false);
        try {
            FXMLLoader loader = new FXMLLoader(SeriesUpdater.class.getResource("view/dialog.fxml"));
            FlowPane layout = (FlowPane)loader.load();            
            Scene scene = new Scene(layout);            
            Button dial_btn = (Button)layout.lookup("#dial_btn");
            dial_btn.setText(btnText);
            dial_btn.setOnAction(new CloseHandler(stage));
            Label dial_title = (Label)layout.lookup("#dial_title");
            dial_title.setText(title);
            Label dial_info = (Label)layout.lookup("#dial_info");
            dial_info.setText(info);
            Label dial_label = (Label)layout.lookup("#dial_label");
            dial_label.setText(label);
            ImageView dial_img = (ImageView)layout.lookup("#dial_img");
            dial_img.setImage(img);
            TextField dial_field = (TextField)layout.lookup("#dial_field");
            dial_field.setVisible(false);
            stage.setScene(scene);
        } catch (IOException ex) {
            
        }
                
        stage.showAndWait();
        return answer;
    }
    
    public static String showPromptDialog(String title, String info, String label, Image img, String btnText, String fieldText) {
        answer = -1;
        
        Stage stage = new Stage();
        stage.setTitle(title);
        stage.setResizable(false);
        try {
            FXMLLoader loader = new FXMLLoader(SeriesUpdater.class.getResource("view/dialog.fxml"));
            FlowPane layout = (FlowPane)loader.load();            
            Scene scene = new Scene(layout);            
            Button dial_btn = (Button)layout.lookup("#dial_btn");
            dial_btn.setText(btnText);
            dial_btn.setOnAction(new CloseHandler(stage));
            Label dial_title = (Label)layout.lookup("#dial_title");
            dial_title.setText(title);
            Label dial_info = (Label)layout.lookup("#dial_info");
            dial_info.setText(info);
            Label dial_label = (Label)layout.lookup("#dial_label");
            dial_label.setText(label);
            ImageView dial_img = (ImageView)layout.lookup("#dial_img");
            dial_img.setImage(img);
            TextField dial_field = (TextField)layout.lookup("#dial_field");
            dial_field.setPromptText(fieldText);
            stage.setScene(scene);
            
            stage.showAndWait();
            if (answer == AFFIRMATIVE) {
                return dial_field.getText();
            }
        } catch (IOException ex) {
            
        }
                
        return null;
    }
    
    
    
}
