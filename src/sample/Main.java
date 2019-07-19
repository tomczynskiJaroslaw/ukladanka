package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader root = new FXMLLoader(getClass().getResource("menu.fxml"));
        primaryStage.setTitle("Hello World");
        Scene scene = new Scene(root.load(), 800, 800);
        primaryStage.setScene(scene);
        primaryStage.show();
        Menu menu = root.getController();
        menu.setScene(scene,primaryStage,null);
        //menu.setSceneChanger(new SceneChanger(primaryStage, scene,null));
    }


    public static void main(String[] args) {
        launch(args);
    }
}
