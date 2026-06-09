package tycoon.business;

import tycoon.exceptions.ExcecaoSaldoInsuficiente;
import tycoon.model.User;

public class DonutShop extends ItemMenu {
    
    public DonutShop() { super("Donut Shop", 103680, 0, 51840, 24000L, 1.12); }

    @Override public String getEmojiName() { return "donut"; }

    @Override public void comprar(User user) throws ExcecaoSaldoInsuficiente { comprarN(user, 1); }

    @Override public void reset_cambio_mundo() {
        this.quantidade = 0;
        this.nextReadyTime = 0; this.managerAtivo = false;
    }
}
