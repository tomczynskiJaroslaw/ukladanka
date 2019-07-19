package sample;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneChanger {
    private Stage stage;
    private Scene scene;
    private MyScene myScene;
    public SceneChanger(SceneChanger sceneChanger, MyScene parent) {
//        this.stage = parent.getSceneChanger().stage;
//        this.scene = parent.getSceneChanger().scene;
        myScene=parent;
    }

    public SceneChanger(Stage stage,Scene scene){
        this.scene=scene;
        this.stage=stage;
    }

    public void changeScene(){
        System.out.println(stage+" "+scene+" "+myScene);
        stage.setScene(scene);
    }
}
