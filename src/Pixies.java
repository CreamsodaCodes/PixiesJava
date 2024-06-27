import java.awt.*;
import java.util.Arrays;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;

public class Pixies extends Sprite{
    boolean isOffspring = false;
    int baseFoodConsumption;
    int originalSize;


    int moveCost;
    int spikes = 1;
    int maxHealth;
    int currentHealth;
    int maxFood;
    int currentFood;
    float herbavoir; //0-1
    static Random random = new Random();
    static int additionalInputs = 9;
    int nodesLayer1;
    int nodesLayer2;

    Interactions[] possibleInteractions;

    BrainInterface brain;

    InputModels[] inputModels;
    BonusActions[] bonusActions;

    double[] inputData;
    double[] outputData;

    //test
    Pixies(int color,int size,int x,int y,Interactions currentInteracrio,float herbavoir,int spikes,int[][] networkStruct){
        this.color = color; length = size; xpos = x; ypos = y;
        Color c = new Color(color);
        red=c.getRed();
        green=c.getGreen();
        blue=c.getBlue();

        this.spikes = spikes;
        this.originalSize = size;
        maxHealth = size * 10;
        currentHealth = maxHealth;
        maxFood = size * 1000; //100
        moveCost = size;
        currentFood = maxFood;
        this.herbavoir = herbavoir;
        this.currentInteraction = currentInteracrio;
        this.possibleInteractions = Interactions.allInteractions;
        VisualManager.drawSquare(x,y,size,color);

        inputModels = new InputModels[]{new TouchPlantsInputModel(this),new TouchMeatInputModel(this),new TouchCreatureInputModel(this),new ClockInputModel(this,100),new ClockInputModel(this,10),new ClockInputModel(this,2),new VisionPlantsInputModel(this,10,0),new VisionPlantsInputModel(this,10,1),new VisionPlantsInputModel(this,10,2),new VisionPlantsInputModel(this,10,3),new VisionMeatInputModel(this,10,0),new VisionMeatInputModel(this,10,1),new VisionMeatInputModel(this,10,2),new VisionMeatInputModel(this,10,3),new VisionCreatureEnemyInputModel(this,10,0),new VisionCreatureEnemyInputModel(this,10,1),new VisionCreatureEnemyInputModel(this,10,2),new VisionCreatureEnemyInputModel(this,10,3),new VisionCreatureFriendInputModel(this,10,0),new VisionCreatureFriendInputModel(this,10,1),new VisionCreatureFriendInputModel(this,10,2),new VisionCreatureFriendInputModel(this,10,3)};
        this.bonusActions = BonusActions.allBonusActions;
        int inputClaculation = 0;
        for (int i = 0; i <inputModels.length ; i++) {
            inputClaculation += inputModels[i].outputSize;
        }
        inputData = new double[inputClaculation+additionalInputs];
        brain = new DJLBrain(9+possibleInteractions.length+bonusActions.length,inputClaculation+additionalInputs,networkStruct);

        int inputModelCost = 0;
        for (InputModels inputModel : inputModels) {
            inputModelCost += inputModel.outputSize;
        }
        inputModelCost = (int) (inputModelCost*0.1);
        this.baseFoodConsumption = inputModelCost + size + spikes; //+ brainsize;
        Settings.currentCreatureCount++;

    }
    // Evolution Constructor
    Pixies(int color,int size,int x,int y,InputModels[] inputModels,int spikes,float herbavoir,BrainInterface brain){
        isOffspring = true;
        this.color = color; length = size;
        xpos = x; ypos = y;
        originalSize = size;
        maxHealth = size * 10;
        maxFood = size * 1000;
        currentHealth = maxHealth;
        currentFood = (int) (maxFood*0.5);
        this.herbavoir = herbavoir;
        this.inputModels = inputModels;
        moveCost = size;
        this.bonusActions = BonusActions.allBonusActions;
        this.possibleInteractions = Interactions.allInteractions;
        //replace with Interaction Chosed by Brain
        currentInteraction = possibleInteractions[0];

        int inputModelCost = 0;
        for (InputModels inputModel : inputModels) {
            inputModelCost += inputModel.outputSize;
        }
        inputModelCost = (int) (inputModelCost*0.1);
        this.baseFoodConsumption = inputModelCost + size + spikes; //+ brainsize;

        VisualManager.drawSquare(x,y,size,color);

        int inputClaculation = 0;
        for (int i = 0; i <inputModels.length ; i++) {
            inputClaculation += inputModels[i].outputSize;
        }
        inputData = new double[inputClaculation+additionalInputs];
        this.brain = brain;
        Settings.currentCreatureCount++;
    }

    void handleAllInputModels(){
        int count = 0;
        inputData[count] = (double) currentHealth/maxHealth;
        count++;
        inputData[count] = (double) currentFood/maxFood;
        count++;
        inputData[count] =  (double) xpos /Settings.getGirdLenght(); //10 = 1  max-10 = 0
        count++;
        inputData[count] =  (double) ypos /Settings.getGridHeight(); //10 = 1  max-10 = 0
        count++;
        inputData[count] =  (double) 1 /xpos; //10 = 1  max-10 = 0
        count++;
        inputData[count] =  (double) 1 /ypos; //10 = 1  max-10 = 0
        count++;
        inputData[count] =  1f;
        count++;
        inputData[count] =  0f;
        count++;

        for (int i = 0; i < inputModels.length; i++) {
            float[] arr = inputModels[i].createData();
            for (int j = 0; j < inputModels[i].outputSize; j++) {
                inputData[count] = arr[j];
                count++;
            }
        }


    }

    void handleFoodConsumption(){
        subCurrentFood(baseFoodConsumption);
    }


    @Override
    void eat(int howManyPlants, int howManyMeat) {
        Settings.setCurrentPlantCount(Settings.currentPlantCount-howManyPlants);


        int foodGain = howManyPlants*Settings.foodGainPerPlant+howManyMeat*Settings.foodGainPerMeat;
        if (foodGain+currentFood>=maxFood) {
            currentFood = maxFood;
        }
        currentFood += foodGain;
    }

    static void spawnPixieAtRandomPosition(){
        int size = random.nextInt(40) + 10;
        int x = random.nextInt(Settings.getGirdLenght()-size-30) + 11;
        int y = random.nextInt(Settings.getGridHeight()-size-30) + 11;

        int red = random.nextInt(254);   // 0 to 254
        int green = random.nextInt(254); // 0 to 254 because plants and meat
        int blue = random.nextInt(254);// blue maybe for smell
        int alpha = 255;
        float herb =(float) ((random.nextInt(200)-100)*0.01);
        int spikes = random.nextInt(100);
        int[][] NetworkStruct = new int[random.nextInt(3)+1][2];
        for (int i = 0; i < NetworkStruct.length; i++) {
            NetworkStruct[i][0] = random.nextInt(50)+1;
            NetworkStruct[i][1] = random.nextInt(10);
        }
        int colour = new Color(red,green,blue,alpha).getRGB();

        if (!GameManager.entitiyHashMap.containsKey(colour)&&VisualManager.isAreaWhite(x,y,size)) {
            GameManager.entitiyHashMap.put(colour,new Pixies(colour,size,x,y,new attackInteraction(),herb,spikes,NetworkStruct));
        }

    }

    void handleThinking(){
        handleFoodConsumption();
        handleAllInputModels();
        outputData = brain.compute(inputData);

        //saving input Data as memory possible
        Arrays.fill(inputData, 0.0);
        //0-8 movement, 9-(9+amount of Interactions),(10+amount of Interactions)-(10+amount of Interactions+amountOfBonusActions)
        int currrentMovementMaxIndex = 0;
        double currentMovementMax = outputData[0];
        for (int i = 1; i < 9; i++) {
            //0-8
            if (outputData[i] > currentMovementMax) {
                currrentMovementMaxIndex = i;
                currentMovementMax = outputData[i];
            }
        }
        int currrentInteractionMaxIndex = 9;
        double currentInteractiontMax = outputData[9];
        for (int i = 10; i < 9+possibleInteractions.length; i++) {
            if (outputData[i] > currentInteractiontMax) {
                //9-10
                currrentInteractionMaxIndex = i;
                currentInteractiontMax = outputData[i];
            }
        }
        int currrentBonusActionMaxIndex = 9+possibleInteractions.length;
        double currentBonusActionMax = outputData[currrentBonusActionMaxIndex];
        for (int i = 10+possibleInteractions.length; i < 9+possibleInteractions.length+bonusActions.length; i++) {
            if (outputData[i] > currentBonusActionMax) {
                //11-12
                currrentBonusActionMaxIndex = i;
                currentBonusActionMax = outputData[i];
            }
        }
        if (currrentMovementMaxIndex != 0) {
            subCurrentFood(moveCost);
        }
        handleMovement(currrentMovementMaxIndex);
        currentInteraction = possibleInteractions[currrentInteractionMaxIndex-9];
        bonusActions[currrentBonusActionMaxIndex-(9+possibleInteractions.length)].activate(this);

    }



    





    void die(){
        if (isDead) {
            return;
        }
        isDead = true;
        drawMeWhite();
        GameManager.entitiyHashMap.remove(color);
        Settings.currentCreatureCount--;

    }

    void drawMeWhite(){
        VisualManager.drawSquare(this.xpos,this.ypos,this.length,Color.WHITE);
        if (isOffspring) {
            int meatSize;
            if (0.05 * getCurrentFood()<length) {
                meatSize = (int) (0.05 * getCurrentFood());
            }
            else{
                meatSize = length;
            }
            VisualManager.drawSquare(this.xpos,this.ypos,meatSize, Color.RED);
        }




    }

    void takeDamageOrHeal(int amount){
        if (currentHealth-amount<=0) {
            die();
            return;
        }

        setCurrentHealth(currentHealth-amount);
    }








    //Getters and Setters

    public int getBaseFoodConsumption() {
        return baseFoodConsumption;
    }


    public void setBaseFoodConsumption(int baseFoodConsumption) {
        this.baseFoodConsumption = baseFoodConsumption;
    }

    public int getOriginalSize() {
        return originalSize;
    }

    public void setOriginalSize(int originalSize) {
        this.originalSize = originalSize;
    }

    public int getSpikes() {
        return spikes;
    }

    public void setSpikes(int spikes) {
        this.spikes = spikes;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public void setCurrentHealth(int currentHealth) {
        if (currentHealth < maxHealth) {
            this.currentHealth = maxHealth;
            return;
        }
        this.currentHealth = currentHealth;
    }

    public int getMaxFood() {
        return maxFood;
    }

    public void setMaxFood(int maxFood) {
        this.maxFood = maxFood;
    }

    public int getCurrentFood() {
        return currentFood;
    }

    public void setCurrentFood(int currentFood) {
        this.currentFood = currentFood;
    }
    public void subCurrentFood(int amount) {
        if (amount >= currentFood) {
            die();
            return;
        }
        this.currentFood -= amount;
    }

    public float getHerbavoir() {
        return herbavoir;
    }

    public void setHerbavoir(float herbavoir) {
        this.herbavoir = herbavoir;
    }




}
