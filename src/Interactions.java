public abstract class Interactions {
    //abstract ?
    abstract void handel(int colour, Pixies me);

    void handel(int[] colours, Pixies me){
        for (int colour : colours) {
            handel(colour, me);
        }
    }
    public static Interactions[] allInteractions = {new DoNothingInteraction(),new attackInteraction()};
}
