package tycoon.progression;

import tycoon.business.ItemMenu;
import tycoon.business.HockeyTeam;

public class DawnCheri extends Managers {
    private static final int PRECO = 111_111_111;

    public DawnCheri() {
        super("Dawn Cheri", PRECO);
    }

    @Override
    public void action(ItemMenu item) {
        if (item instanceof HockeyTeam) {
            item.setManager(true);
        }
    }
}
