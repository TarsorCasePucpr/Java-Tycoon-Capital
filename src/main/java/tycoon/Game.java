package tycoon;

import javax.swing.Timer;
import tycoon.business.CarWash;
import tycoon.business.DonutShop;
import tycoon.business.HockeyTeam;
import tycoon.business.ItemMenu;
import tycoon.business.Lemon;
import tycoon.business.NewspaperDelivery;
import tycoon.business.PizzaDelivery;
import tycoon.business.ShrimpBoat;
import tycoon.model.User;

public class Game {
    private User user;
    private Timer gameLoop;
    private Runnable onMoneyChanged;

    public void inicializarLojas() {
        user.addItem(new Lemon());
        user.addItem(new NewspaperDelivery());
        user.addItem(new CarWash());
        user.addItem(new PizzaDelivery());
        user.addItem(new DonutShop());
        user.addItem(new ShrimpBoat());
        user.addItem(new HockeyTeam());
    }

    public Game(User user) {
        this.user = user;
    }

    public void setOnMoneyChangedListener(Runnable listener) {
        this.onMoneyChanged = listener;
    }

    public void start() {
        gameLoop = new Timer(100, e -> update());
        gameLoop.start();
    }

    public void stop() {
        if (gameLoop != null) gameLoop.stop();
    }

    private void update() {
        long moneyBefore = user.getMoney();

        for (ItemMenu item : user.getItems()) {
            item.tickManager(user);
        }

        if (user.getMoney() != moneyBefore && onMoneyChanged != null) {
            onMoneyChanged.run();
        }
    }
}
