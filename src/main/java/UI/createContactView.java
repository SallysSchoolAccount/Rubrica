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

public class createContactView {
    private VBox vBox;
    private Runnable onContactCreated;
    private Runnable onCancel;

    private TextField vornameField;
    private TextField nachnameField;
    private TextField telefonnummerField;
    private TextField emailField;

    public void setOnContactCreated(Runnable onContactCreated) {
        this.onContactCreated = onContactCreated;
    }
    public void setOnCancel(Runnable onCancel) {
        this.onCancel = onCancel;
    }

    public Node createNewContactForm() {
        vBox = new VBox();
        vBox.setPadding(new Insets(30));
        vBox.setAlignment(Pos.TOP_CENTER);
        vBox.getStyleClass().add("contact-detail");

        uiElements pfp = new uiElements();
        vBox.getChildren().add(pfp.createPfp(150, 150));

        Label titleLabel = new Label("New Contact");
        titleLabel.setFont(Font.font("System", FontWeight.BOLD, 24));
        titleLabel.setPadding(new Insets(10, 0, 20, 0));
        vBox.getChildren().add(titleLabel);

        VBox editBox = new VBox(15);
        editBox.setAlignment(Pos.CENTER_LEFT);
        editBox.setPadding(new Insets(20, 0, 0, 0));

        vornameField = new TextField();
        vornameField.setPromptText("Enter first name");
        HBox vornameBox = createEditField("First Name:", vornameField);

        nachnameField = new TextField();
        nachnameField.setPromptText("Enter last name");
        HBox nachnameBox = createEditField("Last Name:", nachnameField);

        telefonnummerField = new TextField();
        telefonnummerField.setPromptText("Enter phone number");
        HBox phoneBox = createEditField("Phone:", telefonnummerField);

        emailField = new TextField();
        emailField.setPromptText("Enter email");
        HBox emailBox = createEditField("Email:", emailField);

        editBox.getChildren().addAll(vornameBox, nachnameBox, phoneBox, emailBox);

        // Buttons
        HBox buttonBox = new HBox(10);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setPadding(new Insets(20, 0, 0, 0));

        Button createButton = new Button("Create");
        createButton.setFont(Font.font(14));
        createButton.setPadding(new Insets(10, 20, 10, 20));
        createButton.setOnAction(e -> createContact());

        Button cancelButton = new Button("Cancel");
        cancelButton.setFont(Font.font(14));
        cancelButton.setPadding(new Insets(10, 20, 10, 20));
        cancelButton.setOnAction(e -> {
            clearForm();
            if (onCancel != null) {
                onCancel.run();
            }
        });

        buttonBox.getChildren().addAll(createButton, cancelButton);

        vBox.getChildren().addAll(editBox, buttonBox);

        return vBox;
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

    private void createContact() {
        String vorname = vornameField.getText().trim();
        String nachname = nachnameField.getText().trim();
        String telefonnummer = telefonnummerField.getText().trim();
        String email = emailField.getText().trim();

        if (vorname.isEmpty() || nachname.isEmpty()) {
            System.err.println("First name and last name are required!");
            // TODO: Show error message to user
            return;
        }

        Contact newContact = new Contact(0, vorname, nachname, telefonnummer, email);

        try {
            ContactDAOImpl contactDAO = new ContactDAOImpl();
            contactDAO.insert(newContact);
            clearForm();
            if (onContactCreated != null) {
                onContactCreated.run();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // TODO: Show error message to user
            System.err.println("Failed to create contact: " + e.getMessage());
        }
    }

    private void clearForm() {
        vornameField.clear();
        nachnameField.clear();
        telefonnummerField.clear();
        emailField.clear();
    }
}
