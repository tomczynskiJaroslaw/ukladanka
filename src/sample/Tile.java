package sample;


import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Tile {
    static Tile mydlo;

    ImageView image;
    int xId;
    int yId;
    int x;
    int y;

    public Tile(ImageView imageView, int xId, int yId) {
        this.image = imageView;
        this.xId = xId;
        this.yId = yId;
        x=xId;
        y=yId;
    }

    public static void ustawMydlo(Tile klocek){
        mydlo=klocek;
    }

    public void swapTiles() {
        if (!klikalny()) return;
        Tile klocek = this;
        Image tmp = klocek.image.getImage();
        klocek.image.setImage(mydlo.image.getImage());
        mydlo.image.setImage(tmp);
        //zanien xId
        int xId = klocek.xId;
        klocek.xId=mydlo.xId;
        mydlo.xId=xId;
        //zamien y
        int yId = klocek.yId;
        klocek.yId=mydlo.yId;
        mydlo.yId=yId;
        //mydlo <- klocek
        mydlo = klocek;
    }

    public boolean klikalny(){
        if (Math.abs(x-mydlo.x)==1 && Math.abs(y-mydlo.y)==0){
            return true;
        }
        if (Math.abs(x-mydlo.x)==0 && Math.abs(y-mydlo.y)==1){
            return true;
        }
        return false;
    }


}
