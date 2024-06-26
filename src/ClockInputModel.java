public class ClockInputModel extends InputModels{

    int duration;
    int currentTick = 0;
    public ClockInputModel(Pixies me,int duration) {
        super(1, me);
        this.duration = duration;

    }

    @Override
    float[] createData() {
        if (currentTick == duration) {
            currentTick = 0;
            return new float[]{1f};
        }
        currentTick++;
        return new float[]{0f};

    }
}
