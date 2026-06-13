package tycoon.view;

import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JPanel;
import tycoon.model.User;
import tycoon.progression.Unlocks;

public class ViewUnlocksMenu extends JPanel {
    private static final Color BG_MAIN   = new Color(24, 40, 24);

    public ViewUnlocksMenu(User user, Runnable onMoneyChanged) {
        setBackground(BG_MAIN);
        setLayout(new FlowLayout(FlowLayout.LEFT, 20, 20));
        for (Unlocks unlock : user.getUnlocks()) {
            add(new ViewUnlocks(unlock, user, onMoneyChanged));
        }
    }
}
