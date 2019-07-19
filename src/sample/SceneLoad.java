package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneLoad {
    Scene scene;

    public Scene load(String fxml, Stage stage, MyScene parent){
        if (scene==null){
            scene = loadNew(fxml,stage,parent);
            return scene;
        }else{
            return scene;
        }
    }

    public Scene loadNew(String fxml, Stage stage, MyScene parent){
        FXMLLoader root = new FXMLLoader(getClass().getResource(fxml));
        Scene scene = null;
        try {
            scene = new Scene(root.load(),800,800);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(fxml+" don't exist");
        }
        stage.setScene(scene);
        stage.show();
        MyScene myScene = root.getController();
        myScene.setScene(scene,stage,parent);
        return scene;
    }

}
