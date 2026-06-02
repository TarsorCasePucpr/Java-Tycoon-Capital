package tycoon;

import javax.swing.Timer;
import tycoon.model.User;
import tycoon.business.ItemMenu;

public class Game {
    private User user;
    private Timer gameLoop;

    public Game(User user) {
        this.user = user;
    }

    public void start() {
        gameLoop = new Timer(100, e -> update());
        gameLoop.start();
    }

    public void stop() {
        if (gameLoop != null) gameLoop.stop();
    }

    private void update() {
        for (ItemMenu item : user.getItems()) {
            if (item.isReady()) {
                user.addMoney(item.getLucro());
                item.startProduction();
            }
        }
        System.out.println("Saldo: " + user.getMoney());
    }
}
