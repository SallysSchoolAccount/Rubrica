package UI;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public record profilePicture() {
    public Node createPfp(double width, double height) {
        Image image = new Image(getClass().getClassLoader().getResourceAsStream("profilePicture.png"));
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(height);
        imageView.setFitWidth(width);
        imageView.setPreserveRatio(true);
        imageView.setSmooth(true);
        return null;
    }
}
