package tycoon.view;

import java.awt.*;
import javax.swing.*;
import tycoon.model.User;
import tycoon.progression.Investors;

public class ViewInvestors extends JPanel {

    private static final Color BG_MAIN      = new Color(24, 40, 24);
    private static final Color BG_CARD      = new Color(28, 45, 55);
    private static final Color BTN_PRESTIGE = new Color(180, 140, 0);
    private static final Color BTN_OFF      = new Color(60, 60, 60);
    private static final Color TXT_CREAM    = new Color(255, 244, 213);
    private static final Color TXT_GOLD     = new Color(245, 197, 24);
    private static final Color TXT_ANGEL    = new Color(190, 215, 255);

    private final User user;
    private final Runnable onPrestige;
    private JLabel labelCurrentAngels;
    private JLabel labelMultiplier;
    private JLabel labelPendingAngels;
    private JButton btnPrestige;

    public ViewInvestors(User user, Runnable onPrestige) {
        this.user = user;
        this.onPrestige = onPrestige;
        setLayout(new BorderLayout());
        setBackground(BG_MAIN);
        add(buildHeader(), BorderLayout.NORTH);
        add(buildCenter(), BorderLayout.CENTER);
        new Timer(500, e -> atualizar()).start();
    }

    private JPanel buildHeader() {
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(15, 30, 15));
        header.setBorder(BorderFactory.createEmptyBorder(10, 16, 10, 16));
        header.setPreferredSize(new Dimension(0, 52));

        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        titlePanel.setBackground(new Color(15, 30, 15));
        ImageIcon angelIcon = EmojiIcon.get("angel", 28);
        if (angelIcon != null) titlePanel.add(new JLabel(angelIcon));
        JLabel title = new JLabel("Angel Investors");
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setForeground(TXT_GOLD);
        titlePanel.add(title);
        header.add(titlePanel, BorderLayout.WEST);

        JLabel subtitle = new JLabel("+2% income per angel  ·  angels persist through restarts");
        subtitle.setFont(new Font("Arial", Font.PLAIN, 12));
        subtitle.setForeground(new Color(160, 200, 160));
        header.add(subtitle, BorderLayout.EAST);

        return header;
    }

    private JPanel buildCenter() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(BG_MAIN);
        panel.setBorder(BorderFactory.createEmptyBorder(18, 48, 18, 48));

        panel.add(buildActiveCard());
        panel.add(Box.createVerticalStrut(18));
        panel.add(buildPendingCard());
        panel.add(Box.createVerticalStrut(28));
        panel.add(buildPrestigeButton());
        panel.add(Box.createVerticalStrut(10));
        panel.add(buildWarningLabel());
        panel.add(Box.createVerticalGlue());

        return panel;
    }

    private JPanel buildActiveCard() {
        JPanel card = makeStatCard();

        JLabel title = new JLabel("Active Angels", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 13));
        title.setForeground(new Color(150, 175, 200));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(title);

        card.add(Box.createVerticalStrut(6));

        labelCurrentAngels = new JLabel("0", SwingConstants.CENTER);
        labelCurrentAngels.setFont(new Font("Arial", Font.BOLD, 48));
        labelCurrentAngels.setForeground(TXT_ANGEL);
        labelCurrentAngels.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(labelCurrentAngels);

        card.add(Box.createVerticalStrut(4));

        labelMultiplier = new JLabel("+0% income bonus", SwingConstants.CENTER);
        labelMultiplier.setFont(new Font("Arial", Font.PLAIN, 13));
        labelMultiplier.setForeground(TXT_GOLD);
        labelMultiplier.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(labelMultiplier);

        return card;
    }

    private JPanel buildPendingCard() {
        JPanel card = makeStatCard();

        JLabel title = new JLabel("Angels Available to Claim", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 13));
        title.setForeground(new Color(150, 175, 200));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(title);

        card.add(Box.createVerticalStrut(6));

        labelPendingAngels = new JLabel("0", SwingConstants.CENTER);
        labelPendingAngels.setFont(new Font("Arial", Font.BOLD, 40));
        labelPendingAngels.setForeground(new Color(255, 220, 100));
        labelPendingAngels.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(labelPendingAngels);

        card.add(Box.createVerticalStrut(4));

        JLabel info = new JLabel("Based on total lifetime earnings");
        info.setFont(new Font("Arial", Font.ITALIC, 11));
        info.setForeground(new Color(130, 155, 130));
        info.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(info);

        return card;
    }

    private JButton buildPrestigeButton() {
        btnPrestige = new JButton("Claim Angels & Restart");
        btnPrestige.setFont(new Font("Arial", Font.BOLD, 15));
        btnPrestige.setForeground(Color.WHITE);
        btnPrestige.setBackground(BTN_PRESTIGE);
        btnPrestige.setFocusPainted(false);
        btnPrestige.setBorderPainted(false);
        btnPrestige.setMaximumSize(new Dimension(360, 50));
        btnPrestige.setPreferredSize(new Dimension(360, 50));
        btnPrestige.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnPrestige.addActionListener(e -> doPrestige());
        return btnPrestige;
    }

    private JLabel buildWarningLabel() {
        JLabel warn = new JLabel("⚠  Resets all businesses and money. Angels and managers persist.");
        warn.setFont(new Font("Arial", Font.ITALIC, 11));
        warn.setForeground(new Color(200, 165, 100));
        warn.setAlignmentX(Component.CENTER_ALIGNMENT);
        return warn;
    }

    private JPanel makeStatCard() {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(BG_CARD);
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(70, 100, 130)),
            BorderFactory.createEmptyBorder(14, 22, 14, 22)
        ));
        card.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.setMaximumSize(new Dimension(520, 135));
        return card;
    }

    private void doPrestige() {
        int pending = Investors.calculateInvestorsToGain(user.getLifetimeEarnings());
        if (pending <= 0) {
            JOptionPane.showMessageDialog(this,
                "Not enough lifetime earnings to gain any angels yet.\nKeep playing and earning money!",
                "No Angels Available", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        int confirm = JOptionPane.showConfirmDialog(this,
            "Claim " + pending + " Angel Investor(s) and restart?\n\n"
            + "• All businesses and money will be reset\n"
            + "• Angels and managers will be kept\n"
            + "• Income multiplier will increase by +" + (pending * 2) + "%",
            "Confirm Prestige", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (confirm == JOptionPane.YES_OPTION) {
            onPrestige.run();
        }
    }

    public void atualizar() {
        int current = user.getInvestors().size();
        int pending = Investors.calculateInvestorsToGain(user.getLifetimeEarnings());
        double bonusPct = (user.getInvestorMultiplier() - 1.0) * 100;

        labelCurrentAngels.setText(String.valueOf(current));
        labelMultiplier.setText(String.format("+%.0f%% income bonus", bonusPct));
        labelPendingAngels.setText(String.valueOf(pending));

        boolean canPrestige = pending > 0;
        btnPrestige.setBackground(canPrestige ? BTN_PRESTIGE : BTN_OFF);
        btnPrestige.setEnabled(canPrestige);
        btnPrestige.setText(canPrestige
            ? "Claim " + pending + " Angel(s) & Restart"
            : "Earn more to claim angels");
    }
}
