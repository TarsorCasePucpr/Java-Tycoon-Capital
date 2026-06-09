package tycoon.business;

import tycoon.exceptions.ExcecaoSaldoInsuficiente;
import tycoon.model.User;

public class HockeyTeam extends ItemMenu {
    
    public HockeyTeam() { super("Hockey Team", 14929920, 0, 7464960, 384000L, 1.10); }

    @Override public String getEmojiName() { return "hockey"; }

    @Override public void comprar(User user) throws ExcecaoSaldoInsuficiente { comprarN(user, 1); }

    @Override public void reset_cambio_mundo() {
        this.quantidade = 0;
        this.nextReadyTime = 0; this.managerAtivo = false;
    }
}
