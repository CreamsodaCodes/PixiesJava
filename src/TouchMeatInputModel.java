public class TouchMeatInputModel extends InputModels{

    TouchMeatInputModel(Pixies me) {
        super((me.length+1)*4,me);
    }

    @Override
    float[] createData() {
        float[] data = new float[outputSize];
        int index = 0;
        for (int i = 0; i < user.length+2; i++) {
            if (VisualManager.isMeat(user.getXpos()-1+i,user.getYpos()-1)){
                data[index] = 1f;
            }
            else {
                data[index] = 0f;
            }
            index++;
        }
        for (int i = 0; i < user.length+2; i++) {
            if (VisualManager.isMeat(user.getXpos()-1+i,user.getYpos()+user.length)){
                data[index] = 1f;
            }
            else {
                data[index] = 0f;
            }
            index++;
        }
        for (int i = 0; i < user.length; i++) {
            if (VisualManager.isMeat(user.getXpos()-1,user.getYpos()+i)){
                data[index] = 1f;
            }
            else {
                data[index] = 0f;
            }
            index++;
        }
        for (int i = 0; i < user.length; i++) {
            if (VisualManager.isMeat(user.getXpos()+user.length,user.getYpos()+i)){
                data[index] = 1f;
            }
            else {
                data[index] = 0f;
            }
            index++;
        }
        return data;
    }
}
