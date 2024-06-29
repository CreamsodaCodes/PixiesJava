public class attackInteraction extends Interactions{

    @Override
    void handel(int colour, Pixies me) {
        //
        if (GameManager.entitiyHashMap.get(colour) == null||me.isDead||GameManager.entitiyHashMap.get(colour).isDead) {

            //System.out.println(GameManager.entitiyHashMap.get(colour)+"-Colour");
            return;

        }

            GameManager.entitiyHashMap.get(colour).takeDamageOrHeal(me.getSpikes()*2);
            //me.subCurrentFood(1);

    }
}
