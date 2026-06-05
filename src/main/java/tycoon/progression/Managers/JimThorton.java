package tycoon.progression;

import tycoon.business.ItemMenu;
import tycoon.business.NewspaperDelivery;
import tycoon.business.PizzaDelivery;

public class JimThorton extends Managers {
    private static final int PRECO = 50000;

    public JimThorton() {
        super("Jim Thorton", PRECO);
    }

    @Override
    public void action(ItemMenu item) {
        if (item instanceof NewspaperDelivery) {
            item.setManager(true);
        }
        if (item instanceof PizzaDelivery) {
            item.setManager(true);
        }
    }
}
