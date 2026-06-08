package tycoon.progression;

import tycoon.business.ItemMenu;
import tycoon.business.DonutShop;

public class JimThorton extends Managers {
    private static final int PRECO = 1_200_000;

    public JimThorton() {
        super("Jim Thorton", PRECO);
    }

    @Override
    public void action(ItemMenu item) {
        if (item instanceof DonutShop) {
            item.setManager(true);
        }
    }
}
