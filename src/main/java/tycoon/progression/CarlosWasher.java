package tycoon.progression;

import tycoon.business.ItemMenu;
import tycoon.business.CarWash;

public class CarlosWasher extends Managers {
    private static final int PRECO = 350_000;

    public CarlosWasher () {
        super("Carlos Washer", PRECO);
    }

    @Override
    public void action(ItemMenu item) {
        if (item instanceof CarWash) {
            item.setManager(true);
        }
    }
}
