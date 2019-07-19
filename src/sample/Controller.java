package sample;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.*;
import java.util.Random;

public class Controller implements MyScene{

    private Task task;
    private int time;

    private static final int tilesCount = 3;
    String imie;

    @FXML
    private Button menu;
    @FXML
    private Label wyswietlaczCzasu;
    @FXML
    private GridPane plansza;
    private Tile[][] tablica = new Tile[tilesCount][tilesCount];
    private Scene parentScene;
    private Stage stage;
    private SceneChanger sceneChanger;
    private Scene scene;
    private MyScene parent;

    @FXML
    public void initialize() throws FileNotFoundException {
//        menu.setOnMouseClicked(event ->{
//            System.out.println("KLIK");
//            parent.changeScene();
//        });
        time();
        nameDialog();
        Image[][] wycinek = getImage();
        createImage(wycinek);
        createBlanc();
    }

    private void createImage(Image[][] wycinek) {
        for (int i = 0; i< tilesCount; i++){
            for (int j = 0; j< tilesCount; j++){
                ImageView imageView = new ImageView(wycinek[i][j]);
                new Animation(imageView).animation();
                Tile klocek = new Tile(imageView,i,j);
                tablica[i][j] = klocek;
                plansza.add(imageView,i,j);
                final int x = i;
                final int y = j;
                imageView.setOnMousePressed((e)->{
                    tileClick(klocek);
                });
            }
        }
    }

    private void tileClick(Tile klocek) {
        klocek.swapTiles();
        if(isAssembled()){
            task.cancel();
            addScoreToFile();
            alertYouWin();
        }
    }

    private void addScoreToFile() {
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter("wyniki.txt",true);
            fileWriter.append(imie+" "+ time +"\n");
            fileWriter.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    private void createBlanc() {
        Tile mydlo= tablica[0][0];
        double w  = mydlo.image.getImage().getWidth();
        double h = mydlo.image.getImage().getHeight();
        mydlo.image.setImage(new WritableImage((int) w,(int) h));
        Tile.ustawMydlo(mydlo);
    }

    private void time() {
            task = new Task<Integer>() {
                @Override
                protected Integer call() throws Exception {
                    int value = 0;
                    while (!isCancelled()) {
                        time++;
                        Thread.sleep(1000);
                        Platform.runLater(() -> {
                            wyswietlaczCzasu.setText(time + "");
                        });

                    }
                    return value;
                }
            };
            new Thread(task).start();
    }

    private void nameDialog() {
        TextInputDialog textInputDialog = new TextInputDialog();
        textInputDialog.setTitle("wygrana");
        textInputDialog.initStyle(StageStyle.UTILITY);
        textInputDialog.setHeaderText("Podaj swoje imię?");
        textInputDialog.setContentText("imie: ");
        textInputDialog.setGraphic(null);
        textInputDialog.getDialogPane().getStylesheets().add(getClass()
                .getResource("Wyniki.css")
                .toExternalForm());
        imie = textInputDialog.showAndWait().get();
    }

    private void alertYouWin() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("wygrana");
        alert.initStyle(StageStyle.UTILITY);
        alert.setHeaderText("Wygrana");
        alert.setGraphic(null);
        alert.getDialogPane()
            .getStylesheets()
            .add(
                getClass()
                .getResource("Wyniki.css")
                .toExternalForm()
            );
        alert.show();
    }

    public void losuj(){
        Random r = new Random();
        for (int i=0;i<20;i++) {
            int x = r.nextInt(3);
            int y = r.nextInt(3);
            tablica[x][y].swapTiles();
        }
    }

    private boolean isAssembled() {
        for (int x = 0; x< tilesCount; x++){
            for (int y = 0; y< tilesCount; y++){
               if (!((tablica[x][y].xId == x)&&(tablica[x][y].yId == y)))       // if (((tablica[x][y].x != x)||(tablica[x][y].y != y)))
                   return false;
            }
        }
        return true;
    }

    public void wyniki(){

    }

    public static Image[][] getImage( )
    {
        Image obrazek=null;
        try {
            obrazek = new Image(new FileInputStream(new File("obrazek.jpg")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Image[][] imgs =  new Image[tilesCount][tilesCount];
        PixelReader pixelReader =  obrazek.getPixelReader();
        for(int xx = 0; xx < tilesCount; xx++)
        {
            for(int yy = 0; yy < tilesCount; yy++ )
            {
                WritableImage fragment = przerysujFragment(obrazek, pixelReader, xx, yy);

                imgs[xx][yy]=fragment;
            }
        }
        return imgs;
    }

    private static WritableImage przerysujFragment(Image obrazek, PixelReader pixelReader, int xx, int yy) {
        int szerokosc = (int) obrazek.getWidth()/ tilesCount;
        int wysokosc = (int)obrazek.getHeight()/ tilesCount;
        WritableImage fragment = new WritableImage(szerokosc,wysokosc);
        PixelWriter pixelWriter = fragment.getPixelWriter();

        for (int x=0; x<szerokosc; x++){

            for (int y=0; y<wysokosc; y++){
                int vx = xx * szerokosc;
                int vy = yy * wysokosc;
                Color kolor = pixelReader.getColor(x+vx,y+vy);
                pixelWriter.setColor(x,y,kolor);

            }

        }
        return fragment;
    }

    public void setParentScene(Scene parentScene, Stage stage){
        this.parentScene=parentScene;
        this.stage = stage;
    }

    @Override
    public void setScene(Scene scene, Stage stage, MyScene myScene) {
        this.scene = scene;
        this.stage = stage;
        this.parent = myScene;

        new Animation(menu).animation();
        new Exit(menu,"menu.fxml",stage,myScene).pressButton();
    }

    @Override
    public void changeScene() {

    }
}
