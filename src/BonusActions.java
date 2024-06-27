public abstract class BonusActions {
    abstract void activate(Pixies me);
    //propably dumb because there is only an instatiated object in there, so all will have the same
    public static BonusActions[] allBonusActions = {new DoNothingBonusAction(),new MitosisBonusAction(),new HealBonusAction()};
}
