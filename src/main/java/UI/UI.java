package UI;

import DAO.ContactDAOImpl;
import Models.Contact;
import java.util.List;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class UI extends Application {

    private VBox detailPane;

    //Both for launching
    public static void main(String[] args) {
        launch(args);
    }
    public void start(Stage stage) {
        BorderPane root = new BorderPane();

        // Top bar with "Add Contact" button
        HBox topBar = new HBox();
        topBar.setPadding(new Insets(10));
        topBar.setAlignment(Pos.CENTER_RIGHT);
        topBar.getStyleClass().add("top-bar");

        Button addContactButton = new Button("+ New Contact");
        addContactButton.getStyleClass().add("add-contact-button");
        addContactButton.setOnAction(e -> showNewContactForm());

        topBar.getChildren().add(addContactButton);

        // Main content area
        HBox mainLayout = new HBox();

        Region content = createContentLeft();
        ScrollPane contactPane = new ScrollPane(content);
        contactPane.setFitToWidth(true);
        contactPane.setFitToHeight(true);
        contactPane.setPannable(true);
        contactPane.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        content.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
        contactPane.setMaxWidth(310);

        detailPane = new VBox();
        detailPane.setAlignment(Pos.CENTER);
        detailPane.setPrefWidth(590);
        detailPane.getStyleClass().add("detail-pane");
        Label placeholder = new Label("Select a contact to view details");
        placeholder.getStyleClass().add("placeholder-text");
        detailPane.getChildren().add(placeholder);

        mainLayout.getChildren().addAll(contactPane, detailPane);

        root.setTop(topBar);
        root.setCenter(mainLayout);

        Scene scene = new Scene(root, 900, 600);
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
                contactBox box = new contactBox(contact, this::showContactDetails);
                Node visualBox = box.createContactBox();
                result.getChildren().add(visualBox);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public void showContactDetails(Contact contact) {
        ContactDetailView detailView = new ContactDetailView(contact);
        detailView.setOnContactUpdated(this::refreshContactList);
        detailPane.getChildren().clear();
        detailPane.getChildren().add(detailView.createDetailView());
    }

    private void showNewContactForm() {
        createContactView newContactView = new createContactView();
        newContactView.setOnContactCreated(() -> {
            refreshContactList();
            showPlaceholder();
        });
        newContactView.setOnCancel(this::showPlaceholder);
        detailPane.getChildren().clear();
        detailPane.getChildren().add(newContactView.createNewContactForm());
    }
    private void showPlaceholder() {
        detailPane.getChildren().clear();
        Label placeholder = new Label("Select a contact to view details");
        placeholder.getStyleClass().add("placeholder-text");
        detailPane.getChildren().add(placeholder);
    }

    private void refreshContactList() {
        HBox mainLayout = (HBox) detailPane.getParent();
        ScrollPane contactPane = (ScrollPane) mainLayout.getChildren().get(0);

        Region content = createContentLeft();
        contactPane.setContent(content);
        content.setNodeOrientation(javafx.geometry.NodeOrientation.LEFT_TO_RIGHT);
    }
}
