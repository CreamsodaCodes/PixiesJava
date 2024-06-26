public class VisionPlantsInputModel extends InputModels{
    int direction;
    int depth;
    Pixies me;
    VisionPlantsInputModel(Pixies me,int depth,int direction) {
        super(1,me);
        this.direction = direction;
        this.depth=depth;
        this.me=me;
    }

    @Override
    float[] createData() {
        float[] data = new float[outputSize];
        if (direction == 0) {
            for (int i = 0; i < depth; i++) {
                for (int j = 0; j < me.length; j++) {
                    if (VisualManager.isPlant(me.getXpos()+j,me.getYpos()+ me.length + i)) {
                        return new float[]{1f};
                    }

                }
            }
            return new float[]{0f};
        }
        if (direction == 1) {
            for (int i = 0; i < depth; i++) {
                for (int j = 0; j < me.length; j++) {
                    if (VisualManager.isPlant(me.getXpos()+me.length+i,me.getYpos()+ j)) {
                        return new float[]{1f};
                    }

                }
            }
            return new float[]{0f};
        }
        if (direction == 2) {
            for (int i = 0; i < depth; i++) {
                for (int j = 0; j < me.length; j++) {
                    if (VisualManager.isPlant(me.getXpos()+j,me.getYpos()-i)) {
                        return new float[]{1f};
                    }

                }
            }
            return new float[]{0f};
        }
        if (direction == 3) {
            for (int i = 0; i < depth; i++) {
                for (int j = 0; j < me.length; j++) {
                    if (VisualManager.isPlant(me.getXpos()-i,me.getYpos()+j)) {
                        return new float[]{1f};
                    }

                }
            }
            return new float[]{0f};
        }
        return new float[]{0f};
    }
}
