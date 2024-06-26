import java.awt.*;

public class TouchCreatureInputModel extends InputModels{
Pixies me;
    TouchCreatureInputModel(Pixies me) {
        super((me.length+1)*4,me);
        this.me=me;
    }

    @Override
    float[] createData() {
        float[] data = new float[outputSize];
        int index = 0;
        for (int i = 0; i < user.length+2; i++) {
            if (VisualManager.isCreature(user.getXpos()-1+i,user.getYpos()-1)){
                Color other = new Color(VisualManager.gameBoard.getRGB(user.getXpos()-1+i,user.getYpos()-1));
                float distance = (float) (Math.abs(other.getRed() - me.red) + Math.abs(other.getBlue() - me.blue) + Math.abs(other.getGreen() - me.green));
                if (distance <= 30) {
                    data[index] = 1f;
                }
                else {data[index] = 0f;}
            }
            else {
                data[index] = 0f;
            }
            index++;
        }
        for (int i = 0; i < user.length+2; i++) {
            if (VisualManager.isCreature(user.getXpos()-1+i,user.getYpos()+user.length)){
                Color other = new Color(VisualManager.gameBoard.getRGB(user.getXpos()-1+i,user.getYpos()+user.length));
                float distance = (float) (Math.abs(other.getRed() - me.red) + Math.abs(other.getBlue() - me.blue) + Math.abs(other.getGreen() - me.green));
                if (distance <= 30) {
                    data[index] = 1f;
                }
                else {data[index] = 0f;}
            }
            else {
                data[index] = 0f;
            }
            index++;
        }
        for (int i = 0; i < user.length; i++) {
            if (VisualManager.isCreature(user.getXpos()-1,user.getYpos()+i)){
                Color other = new Color(VisualManager.gameBoard.getRGB(user.getXpos()-1,user.getYpos()+i));
                float distance = (float) (Math.abs(other.getRed() - me.red) + Math.abs(other.getBlue() - me.blue) + Math.abs(other.getGreen() - me.green));
                if (distance <= 30) {
                    data[index] = 1f;
                }
                else {data[index] = 0f;}
            }
            else {
                data[index] = 0f;
            }
            index++;
        }
        for (int i = 0; i < user.length; i++) {
            if (VisualManager.isCreature(user.getXpos()+user.length,user.getYpos()+i)){
                Color other = new Color(VisualManager.gameBoard.getRGB(user.getXpos()+user.length,user.getYpos()+i));
                float distance = (float) (Math.abs(other.getRed() - me.red) + Math.abs(other.getBlue() - me.blue) + Math.abs(other.getGreen() - me.green));
                if (distance <= 30) {
                    data[index] = 1f;
                }
                else {data[index] = 0f;}
            }
            else {
                data[index] = 0f;
            }
            index++;
        }
        return data;
    }
}
