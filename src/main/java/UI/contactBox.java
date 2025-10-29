package UI;

import Models.Contact;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class contactBox {
    Contact contact = new Contact(1, "Mario", "Rossi", "0123456789", "");

    public Node createContactBox() {
        HBox hBox = new HBox(40, createPfp(), createName());
        VBox vBox = new VBox(40, hBox);
        vBox.setPadding(new Insets(10, 10, 10, 10));
        return vBox;
    }

    private Node createName() {
        String vorname = contact.getVorname();
        String nachname = contact.getNachname();
        Label result = new Label(vorname + " " + nachname);
        result.setPadding(new Insets(10, 0, 0, 0));
        return result;
    }
    private Node createPfp() {
        Image image = new Image(getClass().getClassLoader().getResourceAsStream("profilePicture.png"));
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(50);
        imageView.setFitWidth(50);
        imageView.setPreserveRatio(true);
        imageView.setSmooth(true);
        return imageView;
    }
}
