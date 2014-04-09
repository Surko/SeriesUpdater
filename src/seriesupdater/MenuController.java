/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package seriesupdater;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import seriesupdater.dialogs.Dialog;
import seriesupdater.information.Profile;

/**
 *
 * @author kirrie
 */
public class MenuController {
        
    // <editor-fold defaultstate="collapsed" desc=" Items from FXML ">
    @FXML
    private MenuItem new_profile;
    @FXML
    private MenuItem load_profile;
    @FXML
    private MenuItem save_profile;
    @FXML
    private MenuItem close;
    
    @FXML
    private MenuItem about;
    // </editor-fold>
    
    private SeriesUpdater app;
     
    public void setMain(SeriesUpdater app) {
        this.app = app;
    }
    
    // <editor-fold defaultstate="collapsed" desc=" Handling events ">
    /**
     * 
     */
    public void close() {
        try {
            Platform.exit();
        } catch (Exception ex) {                    
        }
    }
    
    public void loadProfile() {
        FileChooser chooser = new FileChooser();
        
        chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("DAT files (*.dat)", "*.dat"));
        
        File file = chooser.showOpenDialog(app.primaryStage);
        
        ObjectInput input = null;
        try {
            input = (ObjectInput)new ObjectInputStream(new FileInputStream(file));
            app.setProfile((Profile)input.readObject());
        } catch (Exception ex) {
            
        }
        
    }
    
    public void newProfile() {
        String name = Dialog.showPromptDialog("Profile Creation", "Please put the name of a profile in a field below",
                "Name", null, "OK", "<Profile Name>");
        if (name != null && !name.isEmpty())
            app.setProfile(new Profile(name));        
    }
    
    public void saveProfile() {
        FileChooser chooser = new FileChooser();
        
        chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("DAT files (*.dat)", "*.dat"));
        
        File file = chooser.showSaveDialog(app.primaryStage);
        
        try {
            ObjectOutput output = new ObjectOutputStream(new FileOutputStream(file));
            output.writeObject(app.getProfile());
        } catch (Exception ex) {
            
        }                
    }

    public void about() {
        Dialog.showInfoDialog("About", "Info about app",
                "Builded with SceneBuilder by Surko", new Image(getClass().getResource("res/icon.png").toString()), "OK");
    }
    
    // </editor-fold>
    
}
