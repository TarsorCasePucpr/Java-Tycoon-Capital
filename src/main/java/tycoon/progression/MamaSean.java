package tycoon.progression;

import tycoon.business.ItemMenu;
import tycoon.business.PizzaDelivery;

public class MamaSean extends Managers {
    private static final int PRECO = 500_000;

    public MamaSean() {
        super("Mama Sean", PRECO);
    }

    @Override
    public void action(ItemMenu item) {
        if (item instanceof PizzaDelivery) {
            item.setManager(true);
        }
    }
}
