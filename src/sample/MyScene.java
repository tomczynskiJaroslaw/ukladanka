package sample;

import javafx.scene.Scene;
import javafx.stage.Stage;

public interface MyScene {

    void setScene(Scene scene,Stage stage,MyScene myScene);
    void changeScene();
}
