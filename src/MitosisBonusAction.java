import java.awt.*;
import java.util.Random;
public class MitosisBonusAction extends BonusActions{
    static Random random = new Random();
    int foodCost;
    @Override
    void activate(Pixies me) {
        if (Settings.currentCreatureCount >= Settings.maxCreatureCount) {
            return;
        }
        foodCost = (int)(me.maxFood*0.5);
        if (foodCost >= me.currentFood-10) {
            return;
        }
        mutate(me);

    }
    void mutate(Pixies me){
        int mutatedSpikes;
        int mutatedSize;
        Color oldColor;
        int mutatedColorInt;
        float mutatedHerbavoir;
        BrainInterface mutatedBrain;
        //random.nextInt(1000)+1;

        oldColor = new Color(me.color);
        int red = oldColor.getRed();  // 0 to 254
        int green = oldColor.getGreen(); // 0 to 254 because plants and meat
        int blue = oldColor.getBlue();// blue maybe for smell
        int alpha = 255;
        //bevorzugt mittelwerte
        int redRandom = random.nextInt(10)* (random.nextBoolean() ? 1 : -1);
        int blueRandom = random.nextInt(10)*(random.nextBoolean() ? 1 : -1);
        int greenRandom = random.nextInt(10)*(random.nextBoolean() ? 1 : -1);
        if (red+redRandom<254&&red+redRandom>=0&&green+greenRandom<254&&green+greenRandom>=0&&blue+blueRandom<254&&blue+blueRandom>=0) {
            mutatedColorInt = new Color(red+redRandom,green+greenRandom,blue+blueRandom,alpha).getRGB();
        }
        else{
            redRandom = random.nextInt(10)* (random.nextBoolean() ? 1 : -1);
            blueRandom = random.nextInt(10)*(random.nextBoolean() ? 1 : -1);
            greenRandom = random.nextInt(10)*(random.nextBoolean() ? 1 : -1);
            if (red+redRandom<254&&red+redRandom>=0&&green+greenRandom<254&&green+greenRandom>=0&&blue+blueRandom<254&&blue+blueRandom>=0) {
                mutatedColorInt = new Color(red+redRandom,green+greenRandom,blue+blueRandom,alpha).getRGB();
            }
            else{
                return;
            }
        }




        if (Settings.mutationRate >= random.nextInt(1000)+1) {
            mutatedSpikes = me.getSpikes()+random.nextInt(3)-1;
            if (mutatedSpikes < 0 || mutatedSpikes > 100) {
                mutatedSpikes = me.getSpikes();
            }

        }
        else{
            mutatedSpikes = me.getSpikes();
        }

        if (Settings.mutationRate >= random.nextInt(1000)+1) {
            mutatedSize = me.getOriginalSize()+random.nextInt(3)-1;
            if (mutatedSize <10 || mutatedSize > 100) {
                mutatedSize = me.getOriginalSize();
            }

        }
        else{
            mutatedSize = me.getOriginalSize();
        }

        if (Settings.mutationRate >= random.nextInt(1000)+1) {
            mutatedHerbavoir =(float) (me.getHerbavoir()+(random.nextInt(150)-75)*0.01);
            if (mutatedHerbavoir < 0 || mutatedHerbavoir > 1) {
                mutatedHerbavoir = me.getHerbavoir();
            }

        }
        else{
            mutatedHerbavoir = me.getHerbavoir();
        }

        if (Settings.mutationRate >= random.nextInt(1000)+1) {
            mutatedBrain = me.brain.copy();
            mutatedBrain.mutatedWeights(0.01);

        }
        else{
            mutatedBrain = me.brain;
        }
        int newX = me.getXpos()+ (random.nextInt(40)+mutatedSize) * (random.nextBoolean() ? 1 : -1);
        int newY = me.getYpos()+ (random.nextInt(40)+mutatedSize) * (random.nextBoolean() ? 1 : -1);

        // InputModel Mutation
        if (newX > 0+10 && newY > 0+10 && newY+mutatedSize < Settings.getGridHeight()-10&& newX+mutatedSize < Settings.getGirdLenght()-10&&!GameManager.entitiyHashMap.containsKey(mutatedColorInt)&&VisualManager.isAreaWhite(newX,newY,mutatedSize)) {
            me.subCurrentFood(foodCost);
            GameManager.entitiyHashMap.put(mutatedColorInt,new Pixies(mutatedColorInt,mutatedSize,newX,newY,me.inputModels,mutatedSpikes,mutatedHerbavoir,mutatedBrain));
        }


    }
}
