package sample;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Wyniki implements MyScene{
    @FXML
    Button menu;
    @FXML
    GridPane tablicaWynikow;
    private Scene scene;

    private Stage stage;

    @FXML
    public void initialize(){
        wczytaj("wyniki.txt");
    }

    private void wczytaj(String plik) {
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File(plik));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int n=0;
        while (scanner.hasNextLine()){
            Label label = new Label(scanner.nextLine());
            tablicaWynikow.add(label,0,n++);
            new Animation(label).animation();
        }
        scanner.close();
    }

    @Override
    public void setScene(Scene scene, Stage stage, MyScene myScene) {

        this.scene = scene;
        this.stage = stage;

        new Animation(menu).animation();
        new Exit(menu,"menu.fxml",stage,myScene).pressButton();
    }

    @Override
    public void changeScene() {

    }
}
