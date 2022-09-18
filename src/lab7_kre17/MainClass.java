package lab7_kre17;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class MainClass extends Application {    
    
    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Лабораторная 7");
        stage.getIcons().add(new Image(MainClass.class.getResourceAsStream("logo.png")));
        Parent root = FXMLLoader.load(getClass().getResource("GUI.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);        
    }

    public static void main(String[] args) {
        launch(args);        
    }
    
}
