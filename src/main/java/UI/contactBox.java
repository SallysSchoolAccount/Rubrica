package UI;

import Models.Contact;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.function.Consumer;


public class contactBox {
    private final Contact contact;
    private final Consumer<Contact> onClick;

    public contactBox(Contact contact, Consumer<Contact> onClick) {
        this.contact = contact;
        this.onClick = onClick;
    }

    public Node createContactBox() {
        uiElements pfp = new uiElements();
        HBox hBox = new HBox(40, pfp.createPfp(50, 50), createName());

        VBox vBox = new VBox(40, hBox);
        vBox.setPadding(new Insets(10, 10, 10, 10));
        vBox.setMaxWidth(300);
        vBox.getStyleClass().add("contact-card"); //Stylesheet

//      For making the whole box clickable
        vBox.setCursor(Cursor.HAND);
        vBox.setPickOnBounds(true);
        vBox.setOnMouseClicked(evt -> {
            System.out.println("Clicked: " + contact.toString());
            onClick.accept(contact);
        });

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
}
