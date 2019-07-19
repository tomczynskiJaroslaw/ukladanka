package sample;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Menu implements MyScene{
    private Stage stage;
    private Scene scene;

    @FXML
    Button play;

    @FXML
    Button wyniki;

    @FXML
    public void initialize(){ }

    @Override
    public void setScene(Scene scene, Stage stage, MyScene myScene) {
        this.scene = scene;
        this.stage = stage;

        new Animation(play).animation();
        new Animation(wyniki).animation();
        new Exit(play,"sample.fxml",stage,this).pressButton();
        new Exit(wyniki,"Wyniki.fxml",stage,this).pressButton();
    }

    @Override
    public void changeScene() {
        stage.setScene(scene);
        play.setScaleX(1);
        play.setScaleY(1);
        wyniki.setScaleX(1);
        wyniki.setScaleY(1);
    }
}
