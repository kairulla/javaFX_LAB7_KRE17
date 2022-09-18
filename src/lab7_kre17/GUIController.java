package lab7_kre17;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.logging.Handler;
import java.util.logging.FileHandler;
import java.util.logging.SimpleFormatter;
import java.util.logging.Level;
import org.apache.log4j.BasicConfigurator;

public class GUIController implements Initializable {
    
    static {        
        System.setProperty("java.util.logging.SimpleFormatter.format",
                "%1$tF %1$tT [%4$-7s] %3$s - %5$s %n");
    }    
    static java.util.logging.Logger log = java.util.logging.Logger.getLogger(GUIController.class.getName());
    static org.apache.log4j.Logger log4j = org.apache.log4j.Logger.getLogger(GUIController.class);
    
    @FXML
    private TextField textFieldA;
    @FXML
    private TextField textFieldB;
    @FXML
    private TextField textFieldX;
    @FXML
    private Label labelOtvet;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {        
    }    

    @FXML
    private void buttonSolveAction(ActionEvent event) throws java.io.IOException {
        Handler fileHandler = new FileHandler("logging.log", 100 *  1024, 3, true);
        fileHandler.setFormatter(new SimpleFormatter());
        log.addHandler(fileHandler);
        log.setLevel(Level.ALL);
        BasicConfigurator.configure();
        
        double a = 0, b = 0, x = 0, y = 0;
        try {
            a = Double.parseDouble(textFieldA.getText());
            b = Double.parseDouble(textFieldB.getText());
            x = Double.parseDouble(textFieldX.getText());          
        } catch (Exception ex) {            
            log.log(Level.SEVERE, "My Exception: {0}", ex.getMessage());
            log4j.fatal("Введены неверные данные!!!");
            buttonCleanAction(null);
            textFieldA.requestFocus();
//            ex.printStackTrace();
        }
        
        if (x >= 8) {
            y = (a * a + 4 * x * x + b) / (2.0 * x);            
        } else {
            y = a * a - 2 * x * x;
        }
        
        if (!(Double.isNaN(y)) && (!Double.isInfinite(y))) {
            //labelOtvet.setText("Ответ: " + String.format("%.2f", y));
            labelOtvet.setText(String.valueOf(y));            
        } else {
            labelOtvet.setText("Нет решения!");
            log4j.fatal("Нет решения!");
        }        
              
        log.log(Level.INFO, "A = " + String.valueOf(a) + ", B = " + String.valueOf(b) + ", X = " + String.valueOf(x));
        log.log(Level.WARNING, "ОТВЕТ = " + String.valueOf(y));
        log4j.info("A = " + String.valueOf(a) + ", B = " + String.valueOf(b) + ", X = " + String.valueOf(x));
        log4j.warn("ОТВЕТ = " + String.valueOf(y));
        org.apache.log4j.LogManager.shutdown();
    }

    @FXML
    private void buttonCleanAction(ActionEvent event) {
        labelOtvet.setText("Ответ: ");
        textFieldA.setText("");
        textFieldB.setText("");
        textFieldX.setText("");
    }

    @FXML
    private void buttonExitAction(ActionEvent event) {
        System.exit(0);
    }
    
}
