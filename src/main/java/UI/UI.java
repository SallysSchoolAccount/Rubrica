package UI;

import DAO.ContactDAOImpl;
import Models.Contact;
import java.util.List;
import javafx.application.Application;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class UI extends Application {

    //Both for launching
    public static void main(String[] args) {
        launch(args);
    }
    public void start(Stage stage) {
        Region content = createContentLeft();
        ScrollPane scrollPane = new ScrollPane(content);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        scrollPane.setPannable(true);
        scrollPane.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        content.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
        Scene scene = new Scene(scrollPane, 900, 600);
        scene.getStylesheets().add(getClass().getResource("/stylesheet.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    private Region createContentLeft() {
        VBox result = new VBox(60);

        result.setSpacing(0);
        result.setMaxWidth(310);
        result.setAlignment(Pos.BASELINE_LEFT);

        try {
            ContactDAOImpl contactDAO = new ContactDAOImpl();
            List<Contact> contacts = contactDAO.getAll();
            for (Contact contact : contacts) {
                contactBox box = new contactBox(contact);
                Node visualBox = box.createContactBox();
                result.getChildren().add(visualBox);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
