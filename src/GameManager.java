import java.awt.*;
import java.util.Arrays;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.Thread.sleep;

public class GameManager {

    private static Thread gameLoop;
    private static Thread gameLoop2;
    private static boolean isRunning = true;
    static ConcurrentHashMap<Integer, Pixies> entitiyHashMap = new ConcurrentHashMap<>();
    static int renderOnlyAllXTimes = 0;
    public static void main(String[] args) throws InterruptedException {
        VisualManager.setUp();
        while (!VisualManager.setUpDone){
            //Davor alles initialisieren!
            sleep(10);
            }
        gameLoop2();
        gameLoop2.start();
        gameLoop();
        gameLoop.start();



    }

    public static boolean addToHashMap(int color,Pixies sprite){
        int realColour = new Color(color).getRGB();
        if (entitiyHashMap.containsKey(realColour)) {
            return false;
        }
        entitiyHashMap.put(realColour,sprite);
        return true;
    }



    private static void gameLoop2() {
        gameLoop2 = new Thread(() -> {while (isRunning){

            VisualManager.updateImage(VisualManager.gameBoard);
            try {
                sleep(10);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }

        } });

    }



        private static void gameLoop() {

        System.out.println("a!");




        // initialise the thread
        gameLoop = new Thread(() -> {

            // while the game "is running" and the isRunning boolean is set to true, loop forever
            while (isRunning) {

                keepCreaturessAtWanted();
                keepPlantsAtWanted();
                if (Settings.foodGainPerPlantMin < Settings.foodGainPerPlant && Settings.currentCreatureCount>Settings.wantedCreatureCount) {
                    Settings.foodGainPerPlant--;
                }
                if (Settings.foodGainPerPlantMax > Settings.foodGainPerPlant && Settings.currentCreatureCount<Settings.wantedCreatureCount) {
                    Settings.foodGainPerPlant++;
                }

                //Food.spawnAtRandomPos(0);


                for (Pixies value : entitiyHashMap.values()) {
                    value.handleThinking();
                }

                //gameLogic() (operations on bufferedImage)
                try {
                    sleep(Settings.getWaitTime());
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    static void keepPlantsAtWanted(){
        if (Settings.wantedPlantCount > Settings.currentPlantCount) {
            Food.spawnAtRandomPos(0);
            Food.spawnAtRandomPosSmall(0);
        }
    }
    static void keepCreaturessAtWanted(){
        if (Settings.wantedCreatureCount > Settings.currentCreatureCount) {
            Pixies.spawnPixieAtRandomPosition();
        }
    }
//Bug Fixed! Keeping code if another bug is found
    static void cleanBuggedColors(){
        for (int i = 10; i < Settings.getGridHeight()-10; i++) {
            for (int o = 10; o < Settings.getGirdLenght()-10; o++) {

                if (!entitiyHashMap.containsKey(VisualManager.gameBoard.getRGB(o,i))) {
                    VisualManager.drawSquare(o,i,1,Color.WHITE);
                }
            }
        }
    }
}
