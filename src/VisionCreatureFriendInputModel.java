import java.awt.*;

public class VisionCreatureFriendInputModel extends InputModels {
    int direction;
    int depth;
    Pixies me;

    VisionCreatureFriendInputModel(Pixies me, int depth, int direction) {
        super(1, me);
        this.direction = direction;
        this.depth = depth;
        this.me = me;
    }

    @Override
    float[] createData() {
        float[] data = new float[outputSize];
        if (direction == 0) {
            for (int i = 0; i < depth; i++) {
                for (int j = 0; j < me.length; j++) {
                    if (VisualManager.isCreature(me.getXpos() + j, me.getYpos() + me.length + i)) {
                        Color other = new Color(VisualManager.gameBoard.getRGB(me.getXpos() + j, me.getYpos() + me.length + i));
                        float distance = (float) (Math.abs(other.getRed() - me.red) + Math.abs(other.getBlue() - me.blue) + Math.abs(other.getGreen() - me.green));
                        if (distance <= 30) {
                            return new float[]{1f};
                        }
                    }

                }
            }
            return new float[]{0f};
        }
        if (direction == 1) {
            for (int i = 0; i < depth; i++) {
                for (int j = 0; j < me.length; j++) {
                    if (VisualManager.isCreature(me.getXpos() + me.length + i, me.getYpos() + j)) {
                        Color other = new Color(VisualManager.gameBoard.getRGB(me.getXpos() + me.length + i, me.getYpos() + j));
                        float distance = (float) (Math.abs(other.getRed() - me.red) + Math.abs(other.getBlue() - me.blue) + Math.abs(other.getGreen() - me.green));
                        if (distance <= 30) {
                            return new float[]{1f};
                        }

                    }
                }
            }
            return new float[]{0f};
        }
        if (direction == 2) {
            for (int i = 0; i < depth; i++) {
                for (int j = 0; j < me.length; j++) {
                    if (VisualManager.isCreature(me.getXpos() + j, me.getYpos() - i)) {
                        Color other = new Color(VisualManager.gameBoard.getRGB(me.getXpos() + j, me.getYpos() - i));
                        float distance = (float) (Math.abs(other.getRed() - me.red) + Math.abs(other.getBlue() - me.blue) + Math.abs(other.getGreen() - me.green));
                        if (distance <= 30) {
                            return new float[]{1f};
                        }

                    }
                }

            }
            return new float[]{0f};
        }
        if (direction == 3) {
            for (int i = 0; i < depth; i++) {
                for (int j = 0; j < me.length; j++) {
                    if (VisualManager.isCreature(me.getXpos() - i, me.getYpos() + j)) {
                        Color other = new Color(VisualManager.gameBoard.getRGB(me.getXpos() - i, me.getYpos() + j));
                        float distance = (float) (Math.abs(other.getRed() - me.red) + Math.abs(other.getBlue() - me.blue) + Math.abs(other.getGreen() - me.green));
                        if (distance <= 30) {
                            return new float[]{1f};
                        }

                    }
                }

            }
            return new float[]{0f};
        }
        return new float[]{0f};

    }
}