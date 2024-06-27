import java.awt.*;

public class Food {
    public static void drawPlant(int x,int y,int size){
        Settings.currentPlantCount += size*size;
        VisualManager.drawSquare(x,y,size, Color.GREEN);
    }
    public static void drawMeat(int x,int y,int size){
        VisualManager.drawSquare(x,y,size, Color.RED);
    }
    public static void spawnAtRandomPos(int type){
        int size = Pixies.random.nextInt(40)+1;
        int x = Pixies.random.nextInt(Settings.getGirdLenght()-size-10) + 11;
        int y = Pixies.random.nextInt(Settings.getGridHeight()-size-10) + 11;
        if (VisualManager.isAreaWhite(x,y,size)&&type == 0) {

            drawPlant(x,y,size);
        }

    }
}
