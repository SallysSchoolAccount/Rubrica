package UI;

import DAO.ContactDAOImpl;
import Models.Contact;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.sql.SQLException;

public class ContactDetailView {
    private final Contact contact;
    private boolean isEditing = false;
    private VBox vBox;
    private Runnable onContactUpdated;

    private TextField vornameField;
    private TextField nachnameField;
    private TextField telefonnummerField;
    private TextField emailField;

    public ContactDetailView(Contact contact) {
        this.contact = contact;
    }
    public void setOnContactUpdated(Runnable onContactUpdated) {
        this.onContactUpdated = onContactUpdated;
    }

    public Node createDetailView() {
        if (vBox == null) {
            vBox = new VBox();
            vBox.setPadding(new Insets(30));
            vBox.setAlignment(Pos.TOP_CENTER);
            vBox.getStyleClass().add("contact-detail");
        } else {
            vBox.getChildren().clear();
        }

        uiElements pfp = new uiElements();

        Label nameLabel = new Label(contact.getVorname() + " " + contact.getNachname());
        nameLabel.setFont(Font.font("System", FontWeight.BOLD, 24));

        VBox infoBox = new VBox(10);
        infoBox.setAlignment(Pos.CENTER_LEFT);
        infoBox.setPadding(new Insets(20, 0, 0, 0));

        Label phoneLabel = new Label("Phone: " + contact.getTelefonNumer());
        phoneLabel.setFont(Font.font(16));

        Label emailLabel = new Label("Email: " + contact.getEmail());
        emailLabel.setFont(Font.font(16));

        infoBox.getChildren().addAll(phoneLabel, emailLabel);

        // Edit button
        Button editButton = new Button("Edit");
        editButton.setFont(Font.font(14));
        editButton.setPadding(new Insets(10, 20, 10, 20));
        editButton.setOnAction(e -> toggleEditMode());

        vBox.getChildren().addAll(pfp.createPfp(150, 150), nameLabel, infoBox, editButton);

        return vBox;
    }

    private void toggleEditMode() {
        if (!isEditing) {
            showEditMode();
        } else {
            saveChanges();
            createDetailView();
        }
        isEditing = !isEditing;
    }

    private void showEditMode() {
        vBox.getChildren().clear();

        uiElements pfp = new uiElements();
        uiElements editField = new uiElements();
        vBox.getChildren().add(pfp.createPfp(150, 150));

        VBox editBox = new VBox(15);
        editBox.setAlignment(Pos.CENTER_LEFT);
        editBox.setPadding(new Insets(20, 0, 0, 0));

        vornameField = new TextField(contact.getVorname());
        HBox vornameBox = editField.createEditField("Name: ", vornameField, 100, 200, 14);

        nachnameField = new TextField(contact.getNachname());
        HBox nachnameBox = editField.createEditField("Last Name: ", nachnameField, 100, 200, 14);

        telefonnummerField = new TextField(contact.getTelefonNumer());
        HBox phoneBox = createEditField("Phone:", telefonnummerField);

        emailField = new TextField(contact.getEmail());
        HBox emailBox = createEditField("Email:", emailField);

        editBox.getChildren().addAll(vornameBox, nachnameBox, phoneBox, emailBox);

        // Buttons
        HBox buttonBox = new HBox(10);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setPadding(new Insets(10, 0, 0, 0));

        Button saveButton = new Button("Save");
        saveButton.setFont(Font.font(14));
        saveButton.setPadding(new Insets(10, 20, 10, 20));
        saveButton.setOnAction(e -> {
            saveChanges();
            createDetailView();
            isEditing = false;
        });

        Button cancelButton = new Button("Cancel");
        cancelButton.setFont(Font.font(14));
        cancelButton.setPadding(new Insets(10, 20, 10, 20));
        cancelButton.setOnAction(e -> {
            createDetailView();
            isEditing = false;
        });

        buttonBox.getChildren().addAll(saveButton, cancelButton);

        vBox.getChildren().addAll(editBox, buttonBox);
    }

    private HBox createEditField(String labelText, TextField textField) {
        HBox box = new HBox(10);
        box.setAlignment(Pos.CENTER_LEFT);

        Label label = new Label(labelText);
        label.setFont(Font.font(14));
        label.setPrefWidth(100);

        textField.setPrefWidth(200);

        box.getChildren().addAll(label, textField);
        return box;
    }

    private void saveChanges() {
        contact.setVorname(vornameField.getText());
        contact.setNachname(nachnameField.getText());
        contact.setTelefonNumer(telefonnummerField.getText());
        contact.setEmail(emailField.getText());

        try {
            ContactDAOImpl contactDAO = new ContactDAOImpl();
            contactDAO.update(contact);
            if (onContactUpdated != null) {
                onContactUpdated.run();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // TODO: Show error message to user
            System.err.println("Failed to save contact: " + e.getMessage());
        }
    }

}
