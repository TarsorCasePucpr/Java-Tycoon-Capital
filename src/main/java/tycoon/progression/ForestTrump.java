package tycoon.progression;

import tycoon.business.ItemMenu;
import tycoon.business.ShrimpBoat;

public class ForestTrump extends Managers {
    private static final int PRECO = 10_000_000;

    public ForestTrump() {
        super("Forest Trump", PRECO);
    }

    @Override
    public void action(ItemMenu item) {
        if (item instanceof ShrimpBoat) {
            item.setManager(true);
        }
    }
}
