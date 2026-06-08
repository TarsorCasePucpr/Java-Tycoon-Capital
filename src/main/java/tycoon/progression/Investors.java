package tycoon.progression;

import tycoon.business.ItemMenu;
import tycoon.model.User;

public class Investors {
    private String nome;

    public Investors(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public static double calculateMultiplier(int investorCount) {
        return 0.02 * investorCount;
    }

    public static int calculateInvestorsToGain(long lifetimeEarnings) {
        if (lifetimeEarnings <= 0) return 0;
        return (int) Math.floor(Math.sqrt(lifetimeEarnings / 1000.0));
    }

    public static void prestige(User user) {
        int toGain = calculateInvestorsToGain(user.getLifetimeEarnings());
        if (toGain <= 0) return;

        for (int i = 0; i < toGain; i++) {
            user.addInvestor(new Investors("Angel Investor"));
        }

        for (ItemMenu item : user.getItems()) {
            item.reset_cambio_mundo();
        }

        user.resetMoney();
    }
}
