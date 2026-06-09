package tycoon.business;

import tycoon.exceptions.ExcecaoSaldoInsuficiente;
import tycoon.model.User;

public class CarWash extends ItemMenu {
    
    public CarWash() { super("Car Wash", 720, 0, 540, 6000L, 1.14); }

    @Override public String getEmojiName() { return "car"; }

    @Override public void comprar(User user) throws ExcecaoSaldoInsuficiente { comprarN(user, 1); }

    @Override public void reset_cambio_mundo() {
        this.quantidade = 0;
        this.nextReadyTime = 0; this.managerAtivo = false;
    }
}
