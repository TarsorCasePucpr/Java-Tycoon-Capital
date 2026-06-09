package tycoon.progression;

import tycoon.business.ItemMenu;
import tycoon.business.Lemon;

public class LemonerSilva extends Managers {
    private static final int PRECO = 150_000;

    public LemonerSilva() {
        super("Lemoner Silva", PRECO);
    }

    @Override
    public void action(ItemMenu item) {
        if (item instanceof Lemon) {
            item.setManager(true);
        }
    }
}
