package UI;

import DAO.ContactDAOImpl;
import Models.Contact;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class contactBox {
    private final Contact contact;

    public contactBox() {
        Contact loaded;
        try {
            loaded = new ContactDAOImpl().get(1);
        } catch (Exception e) {
            loaded = null;
        }
        this.contact = loaded;
    }

    public Node createContactBox() {
        HBox hBox = new HBox(40, createPfp(), createName());
        VBox vBox = new VBox(40, hBox);
        vBox.setPadding(new Insets(10, 10, 10, 10));
        vBox.getStyleClass().add("contact-box"); //Stylesheet
        vBox.setCursor(Cursor.HAND);
        vBox.setPickOnBounds(true);
        vBox.setOnMouseClicked(evt -> System.out.println("Clicked: " + contact.getVorname() + " " + contact.getNachname()));
        return vBox;
    }

    private Node createName() {
        String vorname = contact != null ? contact.getVorname() : "";
        String nachname = contact != null ? contact.getNachname() : "";
        Label labelFullname = new Label(vorname + " " + nachname);
        labelFullname.setPadding(new Insets(10, 0, 0, 0));
        labelFullname.getStyleClass().add("contact-name");//Stylesheet
        return labelFullname;
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
