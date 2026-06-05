package tycoon.progression;

import tycoon.business.ItemMenu;
import tycoon.business.CarWash;
import tycoon.business.DonutShop;

public class DawnCheri extends Managers {
    private static final int PRECO = 10000;

    public DawnCheri() {
        super("Dawn Cheri", PRECO);
    }

    @Override
    public void action(ItemMenu item) {
        if (item instanceof CarWash) {
            item.setManager(true);
        }
        if (item instanceof DonutShop) {
            item.setManager(true);
        }
    }
}
