import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class startControl {
    @FXML private Button easy;
    public void easy(ActionEvent event) throws IOException{
        gameLoader(2, event);
    }
    public void medium(ActionEvent event) throws IOException{
        gameLoader(3, event);
    }
    public void hard(ActionEvent event) throws IOException{
        gameLoader(5, event);
    }
    public void impossible(ActionEvent event) throws IOException{
        gameLoader(7, event);
    }

    public void gameLoader (int hardness ,ActionEvent event) throws IOException {
    Parent rootPay = FXMLLoader.load(getClass().getResource("yummy.fxml"));
    Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
    Scene scene = new Scene(rootPay);
        scene.getStylesheets().add(getClass().getResource("Style.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }
/*    Parent rootPay = FXMLLoader.load(getClass().getResource("yummy.fxml"));
    Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
    Scene scene = new Scene(rootPay);
        scene.getStylesheets().add(getClass().getResource("Style.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
*/
}
