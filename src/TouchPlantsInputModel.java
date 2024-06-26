public class TouchPlantsInputModel extends InputModels{

    TouchPlantsInputModel(Pixies me) {
        super(4,me);
    }

    @Override
    float[] createData() {
        float[] data = new float[outputSize];
        int index = 0;
        for (int i = 0; i < user.length+2; i++) {
            if (VisualManager.isPlant(user.getXpos()-1+i,user.getYpos()-1)){
                data[index] = 1f;
                break;
            }
            else {
                data[index] = 0f;
            }

        }
        index++;
        for (int i = 0; i < user.length+2; i++) {
            if (VisualManager.isPlant(user.getXpos()-1+i,user.getYpos()+user.length)){
                data[index] = 1f;
                break;
            }
            else {
                data[index] = 0f;
            }

        }
        index++;
        for (int i = 0; i < user.length; i++) {
            if (VisualManager.isPlant(user.getXpos()-1,user.getYpos()+i)){
                data[index] = 1f;
                break;
            }
            else {
                data[index] = 0f;
            }
        }
        index++;
        for (int i = 0; i < user.length; i++) {
            if (VisualManager.isPlant(user.getXpos()+user.length,user.getYpos()+i)){
                data[index] = 1f;
                break;
            }
            else {
                data[index] = 0f;
            }

        }
        return data;
    }
}
