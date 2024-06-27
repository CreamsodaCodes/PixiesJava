public class HealBonusAction extends BonusActions {
    @Override
    void activate(Pixies me) {
        if (me.maxHealth == me.currentHealth) {
            return;
        }
        me.subCurrentFood(20);
        me.takeDamageOrHeal(-10);



    }
}
