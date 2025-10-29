package UI;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class UI extends Application {

    //Both for launching
    public static void main(String[] args) {
        launch(args);
    }
    public void start(Stage stage) {
        Scene scene = new Scene(createContentLeft(), 1200, 600);
        scene.getStylesheets().add(getClass().getResource("/stylesheet.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    private Region createContentLeft() {
        contactBox contactBox = new contactBox();
        VBox result = new VBox(40, contactBox.createContactBox());
        result.setAlignment(Pos.BASELINE_LEFT);
        return result;
    }
}
