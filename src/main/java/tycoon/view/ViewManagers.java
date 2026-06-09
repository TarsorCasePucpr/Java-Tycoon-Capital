package tycoon.view;

import java.awt.*;
import javax.swing.*;
import tycoon.model.User;
import tycoon.progression.CarlosWasher;
import tycoon.progression.DawnCheri;
import tycoon.progression.ForestTrump;
import tycoon.progression.JimThorton;
import tycoon.progression.LemonerSilva;
import tycoon.progression.Managers;
import tycoon.progression.MamaSean;
import tycoon.progression.NewmanSantos;

public class ViewManagers extends JPanel {

    private static final Color BG_MAIN   = new Color(24, 40, 24);
    private static final Color BG_CARD   = new Color(35, 55, 35);
    private static final Color BTN_HIRE  = new Color(240, 138, 36);
    private static final Color BTN_HIRED = new Color(45, 120, 45);
    private static final Color BTN_OFF   = new Color(60, 60, 60);
    private static final Color TXT_CREAM = new Color(255, 244, 213);
    private static final Color TXT_GOLD  = new Color(245, 197, 24);
    private static final Color TXT_GREEN = new Color(130, 220, 130);

    private final User user;
    private final Runnable onUpdate;
    private final Managers[] allManagers;
    private final JButton[] hireButtons;
    private final JLabel[] costLabels;

    public ViewManagers(User user, Runnable onUpdate) {
        this.user = user;
        this.onUpdate = onUpdate;
        this.allManagers = new Managers[]{
            new LemonerSilva(),
            new NewmanSantos(),
            new CarlosWasher(),
            new MamaSean(),
            new JimThorton(),
            new ForestTrump(),
            new DawnCheri()
        };
        this.hireButtons = new JButton[allManagers.length];
        this.costLabels  = new JLabel[allManagers.length];

        setLayout(new BorderLayout());
        setBackground(BG_MAIN);
        add(buildHeader(), BorderLayout.NORTH);
        add(buildList(),   BorderLayout.CENTER);

        new Timer(200, e -> atualizar()).start();
    }

    private JPanel buildHeader() {
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(15, 30, 15));
        header.setBorder(BorderFactory.createEmptyBorder(12, 16, 12, 16));
        header.setPreferredSize(new Dimension(0, 56));

        JLabel title = new JLabel("👔  Managers");
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setForeground(TXT_GOLD);
        header.add(title, BorderLayout.WEST);

        JLabel subtitle = new JLabel("Hire managers to automate your businesses");
        subtitle.setFont(new Font("Arial", Font.PLAIN, 12));
        subtitle.setForeground(new Color(160, 200, 160));
        header.add(subtitle, BorderLayout.EAST);

        return header;
    }

    private JScrollPane buildList() {
        JPanel list = new JPanel();
        list.setLayout(new BoxLayout(list, BoxLayout.Y_AXIS));
        list.setBackground(BG_MAIN);
        list.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));

        for (int i = 0; i < allManagers.length; i++) {
            list.add(buildManagerCard(i));
            list.add(Box.createVerticalStrut(8));
        }
        list.add(Box.createVerticalGlue());

        JScrollPane scroll = new JScrollPane(list);
        scroll.setBackground(BG_MAIN);
        scroll.getViewport().setBackground(BG_MAIN);
        scroll.setBorder(null);
        scroll.getVerticalScrollBar().setUnitIncrement(16);
        return scroll;
    }

    private JPanel buildManagerCard(int idx) {
        Managers m = allManagers[idx];

        JPanel card = new JPanel(new BorderLayout(10, 0));
        card.setBackground(BG_CARD);
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(140, 100, 50)),
            BorderFactory.createEmptyBorder(12, 14, 12, 14)
        ));
        card.setMaximumSize(new Dimension(Integer.MAX_VALUE, 82));
        card.setPreferredSize(new Dimension(800, 82));

        JLabel emoji = new JLabel(getEmoji(m), SwingConstants.CENTER);
        emoji.setFont(new Font("Dialog", Font.PLAIN, 32));
        emoji.setPreferredSize(new Dimension(50, 58));
        card.add(emoji, BorderLayout.WEST);

        JPanel centerP = new JPanel();
        centerP.setLayout(new BoxLayout(centerP, BoxLayout.Y_AXIS));
        centerP.setBackground(BG_CARD);
        JLabel nameLabel = new JLabel(m.getNome());
        nameLabel.setFont(new Font("Arial", Font.BOLD, 14));
        nameLabel.setForeground(TXT_CREAM);
        centerP.add(nameLabel);
        centerP.add(Box.createVerticalStrut(4));
        JLabel descLabel = new JLabel(getDescription(m));
        descLabel.setFont(new Font("Arial", Font.PLAIN, 11));
        descLabel.setForeground(TXT_GREEN);
        centerP.add(descLabel);
        card.add(centerP, BorderLayout.CENTER);

        JPanel rightP = new JPanel();
        rightP.setLayout(new BoxLayout(rightP, BoxLayout.Y_AXIS));
        rightP.setBackground(BG_CARD);
        rightP.setPreferredSize(new Dimension(155, 58));

        costLabels[idx] = new JLabel("$" + ViewCardShop.formatMoney(m.getPreco()), SwingConstants.CENTER);
        costLabels[idx].setFont(new Font("Arial", Font.BOLD, 12));
        costLabels[idx].setForeground(TXT_GOLD);
        costLabels[idx].setAlignmentX(Component.CENTER_ALIGNMENT);
        rightP.add(costLabels[idx]);
        rightP.add(Box.createVerticalStrut(5));

        hireButtons[idx] = new JButton("Hire");
        hireButtons[idx].setFont(new Font("Arial", Font.BOLD, 12));
        hireButtons[idx].setForeground(Color.WHITE);
        hireButtons[idx].setBackground(BTN_HIRE);
        hireButtons[idx].setFocusPainted(false);
        hireButtons[idx].setBorderPainted(false);
        hireButtons[idx].setMaximumSize(new Dimension(145, 30));
        hireButtons[idx].setAlignmentX(Component.CENTER_ALIGNMENT);

        final int fi = idx;
        hireButtons[idx].addActionListener(e -> hire(fi));
        rightP.add(hireButtons[idx]);

        card.add(rightP, BorderLayout.EAST);
        return card;
    }

    private boolean isHired(int idx) {
        Class<?> cls = allManagers[idx].getClass();
        for (Managers m : user.getManagers()) {
            if (m.getClass().equals(cls)) return true;
        }
        return false;
    }

    private void hire(int idx) {
        if (isHired(idx)) return;
        Managers m = allManagers[idx];
        if (user.getMoney() < m.getPreco()) return;
        user.setMoney(-m.getPreco());
        user.addManager(m);
        onUpdate.run();
    }

    public void atualizar() {
        for (int i = 0; i < allManagers.length; i++) {
            if (isHired(i)) {
                hireButtons[i].setText("✓ Hired");
                hireButtons[i].setBackground(BTN_HIRED);
                hireButtons[i].setEnabled(false);
                costLabels[i].setForeground(new Color(100, 180, 100));
            } else {
                boolean canAfford = user.getMoney() >= allManagers[i].getPreco();
                hireButtons[i].setBackground(canAfford ? BTN_HIRE : BTN_OFF);
                hireButtons[i].setEnabled(true);
            }
        }
    }

    private String getEmoji(Managers m) {
        switch (m.getClass().getSimpleName()) {
            case "MamaSean":    return "🍕";
            case "JimThorton":  return "🍩";
            case "ForestTrump": return "🦐";
            case "DawnCheri":   return "🏒";
            default:            return "👔";
        }
    }

    private String getDescription(Managers m) {
        switch (m.getClass().getSimpleName()) {
            case "LemonerSilva": return "Runs the Lemon automatically";
            case "NewmanSantos": return "Runs the NewspaperDelivery automatically";
            case "CarlosWasher": return "Runs the CarWash automatically";
            case "MamaSean":    return "Runs the Pizza Delivery automatically";
            case "JimThorton":  return "Runs the Donut Shop automatically";
            case "ForestTrump": return "Runs the Shrimp Boat automatically";
            case "DawnCheri":   return "Runs the Hockey Team automatically";
            default:            return "Automates a business";
        }
    }
}
