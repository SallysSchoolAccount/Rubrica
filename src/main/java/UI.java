import Models.Contact;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class UI extends Application {

    Contact contact = new Contact(1, "Mario", "Rossi", "0123456789", "");

    //Both for launching
    public static void main(String[] args) {
        launch(args);
    }
    public void start(Stage stage) {
        Scene scene = new Scene(createContent(), 1200, 600);
        stage.setScene(scene);
        stage.show();
    }

    private Region createContent() {
        VBox result = new VBox(20, createContactBox());
        result.setAlignment(Pos.BASELINE_LEFT);
        return result;
    }

    private Node createContactBox() {
        HBox hBox = new HBox(20, createPfp(), createName());
        VBox vBox = new VBox(20, hBox);
        return vBox;
    }

    private Node createName() {
        String vorname = contact.getVorname();
        String nachname = contact.getNachname();
        Label result = new Label(vorname + " " + nachname);
        return result;
    }
    private Node createPfp() {
        Image image = new Image("profilePicture.png");
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(24);
        imageView.setFitWidth(24);
        imageView.setPreserveRatio(true);
        imageView.setSmooth(true);
        return imageView;
    }
}
