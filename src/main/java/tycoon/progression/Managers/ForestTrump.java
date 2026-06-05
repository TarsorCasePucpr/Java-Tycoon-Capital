package tycoon.progression;

import tycoon.business.ItemMenu;
import tycoon.business.HockeyTeam;
import tycoon.business.Lemon;

public class ForestTrump extends Managers {
    private static final int PRECO = 30000;

    public ForestTrump() {
        super("Forest Trump", PRECO);
    }

    @Override
    public void action(ItemMenu item) {
        if (item instanceof HockeyTeam) {
            item.setManager(true);
        }
        if (item instanceof Lemon) {
            item.setManager(true);
        }
    }
}
