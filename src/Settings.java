public final class Settings {
    static int girdLenght = 2000; //1000-5000 looks good atm
    static int gridHeight = 2000;
    static int displayLenght = 500;
    static int displayHeight = 500;
    static int waitTime = 0;
    static int currentPlantCount = 0;
    static int wantedPlantCount = 18800;//150000
    static int currentCreatureCount = 0;
    static int wantedCreatureCount = 100;
    static int maxCreatureCount = 1000;
    public  static int foodGainPerPlant = 500;//10
    public final static int foodGainPerPlantMax = 700;//10
    public final static int foodGainPerPlantMin = 300;//10

    public final static int foodGainPerMeat = 100; //10
    public final static int mutationRate = 10; //0-1000




    public static int getCurrentPlantCount() {
        return currentPlantCount;
    }

    public static void setCurrentPlantCount(int currentPlantCount) {
        if (currentPlantCount < 0) {
            Settings.currentPlantCount=0;
            return;
        }
        Settings.currentPlantCount = currentPlantCount;
    }
    public static void decreeeseCurrentPlantCount(int currentPlantCount) {
        Settings.currentPlantCount--;
    }

    public static int getWantedPlantCount() {
        return wantedPlantCount;
    }

    public static void setWantedPlantCount(int wantedPlantCount) {
        Settings.wantedPlantCount = wantedPlantCount;
    }

    public static int getWaitTime() {
        return waitTime;
    }

    public static int getDisplayHeight() {
        return displayHeight;
    }

    public static void setDisplayHeight(int displayHeight) {
        Settings.displayHeight = displayHeight;
    }

    public static int getDisplayLenght() {
        return displayLenght;
    }

    public static void setDisplayLenght(int displayLenght) {
        Settings.displayLenght = displayLenght;
    }



    public static int getGirdLenght() {
        return girdLenght;
    }

    public static void setGirdLenght(int girdLenght) {
        Settings.girdLenght = girdLenght;
    }

    public static int getGridHeight() {
        return gridHeight;
    }

    public static void setGridHeight(int gridHeight) {
        Settings.gridHeight = gridHeight;
    }
}
