package tycoon.business;

import tycoon.exceptions.ExcecaoSaldoInsuficiente;
import tycoon.model.User;

public class NewspaperDelivery extends ItemMenu {
    
    public NewspaperDelivery() { super("Newspaper Delivery", 60, 0, 60, 3000L, 1.15); }

    @Override public String getEmojiName() { return "newspaper"; }

    @Override public void comprar(User user) throws ExcecaoSaldoInsuficiente { comprarN(user, 1); }

    @Override public void reset_cambio_mundo() {
        this.quantidade = 0;
        this.nextReadyTime = 0; this.managerAtivo = false;
    }
}
