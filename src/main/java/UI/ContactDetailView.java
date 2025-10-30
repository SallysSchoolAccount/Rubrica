package UI;

import Models.Contact;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class ContactDetailView {
    private final Contact contact;

    public ContactDetailView(Contact contact) {
        this.contact = contact;
    }

    public Node createDetailView() {
        VBox vBox = new VBox();
        vBox.setPadding(new Insets(30));
        vBox.setAlignment(Pos.TOP_CENTER);
        vBox.getStyleClass().add("contact-detail");

        uiElements pfp = new uiElements();

        // Name
        Label nameLabel = new Label(contact.getVorname() + " " + contact.getNachname());
        nameLabel.setFont(Font.font("System", FontWeight.BOLD, 24));

        // Contact details
        VBox infoBox = new VBox(10);
        infoBox.setAlignment(Pos.CENTER_LEFT);
        infoBox.setPadding(new Insets(20, 0, 0, 0));

        Label phoneLabel = new Label("Phone: " + contact.getTelefonNumer());
        phoneLabel.setFont(Font.font(16));

        Label emailLabel = new Label("Email: " + contact.getEmail());
        emailLabel.setFont(Font.font(16));

        infoBox.getChildren().addAll(phoneLabel, emailLabel);

        vBox.getChildren().addAll(pfp.createPfp(150, 150), nameLabel, infoBox);

        return vBox;
    }
}
