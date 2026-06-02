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

    private void inicializarLojas() {
        user.addItem(new CarWash());
        user.addItem(new DonutShop());
        user.addItem(new HockeyTeam());
        user.addItem(new Lemon());
        user.addItem(new NewspaperDelivery());
        user.addItem(new PizzaDelivery());
        user.addItem(new ShrimpBoat());
    }

    public Game(User user) {
        this.user = user;
    }

    public void start() {
        inicializarLojas();
        gameLoop = new Timer(100, e -> update());
        gameLoop.start();
    }

    public void stop() {
        if (gameLoop != null) gameLoop.stop();
    }

    private void update() {
        for (ItemMenu item : user.getItems()) {
            if (item.isReady()) {
                //Implment logic of bottom press or manager automation --10 seconds-- do not exist
                //user.addMoney(item.getLucro());
                item.startProduction();
            }
        }
        System.out.println("Saldo: " + user.getMoney());
    }
}
