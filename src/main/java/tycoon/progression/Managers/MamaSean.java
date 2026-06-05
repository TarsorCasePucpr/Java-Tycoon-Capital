package tycoon.progression;

import tycoon.business.ItemMenu;
import tycoon.business.ShrimpBoat;

public class MamaSean extends Managers {
    private static final int PRECO = 100000;

    public MamaSean() {
        super("Mama Sean", PRECO);
    }

    @Override
    public void action(ItemMenu item) {
        if (item instanceof ShrimpBoat) {
            item.setManager(true);
        }
    }
}
