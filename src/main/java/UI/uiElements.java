package UI;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

public record uiElements() {
    public Node createPfp(double width, double height) {
        Image image = new Image(getClass().getClassLoader().getResourceAsStream("profilePicture.png"));
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(height);
        imageView.setFitWidth(width);
        imageView.setPreserveRatio(true);
        imageView.setSmooth(true);
        return imageView;
    }

    public HBox createEditField(String labelText, TextField textField, double widthLabel, double widthTextfield, double fontSize) {
        HBox box = new HBox(10);
        box.setAlignment(Pos.CENTER_LEFT);

        Label label = new Label(labelText);
        label.setFont(Font.font(fontSize));
        label.setPrefWidth(widthLabel);

        textField.setPrefWidth(widthTextfield);

        box.getChildren().addAll(label, textField);
        return box;
    }
}
