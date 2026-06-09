package tycoon.progression;

import tycoon.business.ItemMenu;
import tycoon.business.NewspaperDelivery;

public class NewmanSantos extends Managers {
    private static final int PRECO = 200_000;

    public NewmanSantos() {
        super("Newman Santos", PRECO);
    }

    @Override
    public void action(ItemMenu item) {
        if (item instanceof NewspaperDelivery) {
            item.setManager(true);
        }
    }
}
