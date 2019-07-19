package sample;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Exit {
    Button button;
    String fxml;
    Scene scene;
    Stage stage;
    MyScene myScene;

    public Exit(Button button, String fxml, Stage stage, MyScene myScene) {
        this.button = button;
        this.fxml = fxml;
        this.stage = stage;
        this.myScene = myScene;
    }



    public void pressButton() {
        button.setOnMouseClicked(e->{
            new Animation(button)
                    .from(1)
                    .to(0)
                    .finishEvent(
                            z->{
                                Platform.runLater(()->{
                                    if (scene==null) {
                                        System.out.println(this);
                                        scene = new SceneLoad().load(fxml, stage, myScene);
                                    }else{
                                        stage.setScene(scene);
                                    }
                                });
                            }
                    ).animation();
        });
    }

}
