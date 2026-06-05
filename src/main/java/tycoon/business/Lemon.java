package tycoon.business;

import tycoon.exceptions.ExcecaoSaldoInsuficiente;
import tycoon.model.User;

public class Lemon extends ItemMenu {
    
    public Lemon() { super("Lemon Stand", 4, 0, 1, 600L, 1.07); }

    @Override public void comprar(User user) throws ExcecaoSaldoInsuficiente { comprarN(user, 1); }

    @Override public void reset_cambio_mundo() {
        this.quantidade = 0;
        this.nextReadyTime = 0; this.managerAtivo = false;
    }
}
