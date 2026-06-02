package tycoon.progression;

import tycoon.business.ItemMenu;
import tycoon.model.User;

public class Investors {
    private static final double multiplier_investor = 0.2;
    
    public double calculateMultiplier(int investorCount) {
        return (multiplier_investor * investorCount);
    }

    
    public int calculateInvestorsToGain(long money) {
        if (money <= 0) return 0;
        return (int) Math.floor(Math.sqrt(money / 1000.0));
    }

    
    public void prestige(User user) {
        int toGain = calculateInvestorsToGain(user.getMoney());
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
