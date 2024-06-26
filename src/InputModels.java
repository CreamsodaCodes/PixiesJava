public abstract class InputModels {
    abstract float[] createData();
    static InputModels[] allInputModels;
    public int outputSize;
    public Pixies user;
    InputModels(int outputSize, Pixies me) {
        this.outputSize = outputSize;
        user = me;
    }
}
