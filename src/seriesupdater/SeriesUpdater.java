/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package seriesupdater;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import seriesupdater.information.Profile;

/**
 *
 * @author kirrie
 */
public class SeriesUpdater extends Application {
        
    private static final String title = "Tv-Series Checker";
    private BorderPane rootLayout;
    private AnchorPane mainLayout;
    public Stage primaryStage;
    
    private Profile activeProfile;
    
    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle(title);
        
        try {
            primaryStage.setMaxWidth(250d);
            primaryStage.setMaxHeight(485d);
            primaryStage.setHeight(485d);
            primaryStage.getIcons().add(new Image(getClass().getResource("res/icon.png").toString()));
            
            mainInit();            
            rootInit();           
            
            Scene scene = new Scene(rootLayout);
            scene.getStylesheets().add(this.getClass().getResource("view/main.css").toExternalForm()); 
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            // Exception gets thrown if the fxml file could not be loaded
            e.printStackTrace();
        }
    }
    
    public void rootInit() throws Exception {
        FXMLLoader loader = new FXMLLoader(SeriesUpdater.class.getResource("view/RootLayout.fxml"));                
        
        // Setting root container
        rootLayout = (BorderPane) loader.load();              
        rootLayout.setCenter(mainLayout);                
        
        // Setting controller
        MenuController controller = loader.getController();
        controller.setMain(this);
    }
    
    public void mainInit() throws Exception {
        FXMLLoader loader = new FXMLLoader(SeriesUpdater.class.getResource("view/MainLayout.fxml"));
        mainLayout = (AnchorPane) loader.load();        
    }
    
    // <editor-fold defaultstate="collapsed" desc=" Getters ">
    public Profile getProfile() {
        return activeProfile;
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc=" Setters ">
    public void setProfile(Profile profile) {
        this.activeProfile = profile;
    }
    // </editor-fold>
}
