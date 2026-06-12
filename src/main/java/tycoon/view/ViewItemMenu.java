package tycoon.view;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import javax.swing.*;
import tycoon.Game;
import tycoon.business.ItemMenu;
import tycoon.model.User;
import tycoon.persistence.Persistence;

public class ViewItemMenu {

    private static final Color BG_MAIN    = new Color(24, 40, 24);    
    private static final Color BG_SIDEBAR = new Color(15, 28, 15);    
    private static final Color BTN_MENU   = new Color(55, 90, 55);    
    private static final Color TXT_CREAM  = new Color(255, 244, 213);
    private static final Color TXT_GOLD   = new Color(245, 197, 24);

    private JFrame janela;
    private User user;
    private Game game;
    private JLabel labelSaldo;
    private int[] buyMode = {1};
    private JButton[] buyModeButtons;
    private CardLayout cardLayout;
    private JPanel centerContainer;
    private ViewManagers viewManagers;
    private ViewInvestors viewInvestors;
    private ViewUpgrades viewUpgrades;

    public void show() {
        try {
            user = Persistence.loadUser("user.dat");
        } catch (Exception e) {
            user = new User();
        }

        game = new Game(user);
        if (user.getItems().isEmpty()) {
            game.inicializarLojas();
        } else {
            user.getItems().forEach(ItemMenu::ensureProductionStarted);
        }

        janela = new JFrame("Java Tycoon Capital");
        janela.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        janela.setSize(1100, 720);
        janela.setLocationRelativeTo(null);
        janela.getContentPane().setBackground(BG_MAIN);

        janela.addWindowListener(new WindowAdapter() {
            @Override public void windowClosing(WindowEvent e) {
                game.stop();
                try { Persistence.saveUser(user, "user.dat"); }
                catch (IOException ex) { ex.printStackTrace(); }
                finally { janela.dispose(); System.exit(0); }
            }
        });

        viewManagers  = new ViewManagers(user, this::atualizarSaldo);
        viewInvestors = new ViewInvestors(user, this::doPrestige);
        viewUpgrades  = new ViewUpgrades(user);

        cardLayout = new CardLayout();
        centerContainer = new JPanel(cardLayout);
        centerContainer.add(buildCenter(),  "game");
        centerContainer.add(viewManagers,   "managers");
        centerContainer.add(viewInvestors,  "investors");
        centerContainer.add(viewUpgrades,   "upgrades");
        centerContainer.add(new ViewUnlocksMenu(user), "unlocks");
        
        janela.setLayout(new BorderLayout(0, 0));
        janela.add(buildTopBar(),    BorderLayout.NORTH);
        janela.add(buildSidebar(),   BorderLayout.WEST);
        janela.add(centerContainer,  BorderLayout.CENTER);

        janela.setVisible(true);

        game.setOnMoneyChangedListener(() -> SwingUtilities.invokeLater(this::atualizarSaldo));
        game.start();
    }

    private JPanel buildTopBar() {
        JPanel top = new JPanel(new BorderLayout(10, 0));
        top.setBackground(BG_SIDEBAR);
        top.setBorder(BorderFactory.createEmptyBorder(8, 14, 8, 14));
        top.setPreferredSize(new Dimension(1100, 58));

        JPanel leftTop = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        leftTop.setBackground(BG_SIDEBAR);
        ImageIcon hatIcon = EmojiIcon.get("hat", 36);
        JLabel avatar = hatIcon != null ? new JLabel(hatIcon) : new JLabel("$");
        leftTop.add(avatar);
        labelSaldo = new JLabel("$" + ViewCardShop.formatMoney(user.getMoney()));
        labelSaldo.setFont(new Font("Arial", Font.BOLD, 40));  
        labelSaldo.setForeground(TXT_GOLD);
        leftTop.add(labelSaldo);
        top.add(leftTop, BorderLayout.WEST);

        JPanel rightTop = new JPanel(new FlowLayout(FlowLayout.RIGHT, 6, 0));
        rightTop.setBackground(BG_SIDEBAR);
        JLabel buyLabel = new JLabel("BUY:");
        buyLabel.setForeground(TXT_CREAM);
        buyLabel.setFont(new Font("Arial", Font.BOLD, 12));
        rightTop.add(buyLabel);

        String[] modes   = {"×1", "×10", "×100", "MAX"};
        int[] modeValues = {1, 10, 100, -1};
        buyModeButtons = new JButton[4];
        for (int i = 0; i < modes.length; i++) {
            final int val = modeValues[i];
            JButton btn = new JButton(modes[i]);
            btn.setFont(new Font("Arial", Font.BOLD, 12));
            btn.setFocusPainted(false);
            btn.setBorderPainted(false);
            btn.setPreferredSize(new Dimension(58, 30));
            btn.addActionListener(e -> { buyMode[0] = val; updateBuyModeButtons(); });
            buyModeButtons[i] = btn;
            rightTop.add(btn);
        }
        updateBuyModeButtons();
        top.add(rightTop, BorderLayout.EAST);
        return top;
    }

    private void updateBuyModeButtons() {
        int[] modeValues = {1, 10, 100, -1};
        for (int i = 0; i < buyModeButtons.length; i++) {
            boolean sel = buyMode[0] == modeValues[i];
            buyModeButtons[i].setBackground(sel ? new Color(232, 120, 32) : new Color(70, 55, 35));
            buyModeButtons[i].setForeground(Color.WHITE);
        }
    }

    private JPanel buildSidebar() {
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setBackground(BG_SIDEBAR);
        sidebar.setPreferredSize(new Dimension(155, 720));
        sidebar.setBorder(BorderFactory.createEmptyBorder(12, 8, 12, 8));

        JButton gameBtn = makeMenuButton("  Game", "game");
        gameBtn.addActionListener(e -> cardLayout.show(centerContainer, "game"));
        sidebar.add(gameBtn);
        sidebar.add(Box.createVerticalStrut(8));

        for (String label : new String[]{"Unlocks", "Upgrades", "Managers", "Investors"}) {
            JButton btn = makeMenuButton(label, null);
            btn.addActionListener(e -> {
                switch (label) {
                    case "Managers":  cardLayout.show(centerContainer, "managers");  break;
                    case "Investors": cardLayout.show(centerContainer, "investors"); break;
                    case "Unlocks":   cardLayout.show(centerContainer, "unlocks"); break;
                    case "Upgrades":  cardLayout.show(centerContainer, "upgrades"); break;
                }
            });
            sidebar.add(btn);
            sidebar.add(Box.createVerticalStrut(8));
        }
        sidebar.add(Box.createVerticalGlue());
        return sidebar;
    }

    private JButton makeMenuButton(String label, String emojiName) {
        JButton btn = new JButton(label);
        btn.setFont(new Font("Arial", Font.BOLD, 13));
        btn.setBackground(BTN_MENU);
        btn.setForeground(TXT_CREAM);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setMaximumSize(new Dimension(139, 44));
        btn.setPreferredSize(new Dimension(139, 44));
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        if (emojiName != null) {
            ImageIcon icon = EmojiIcon.get(emojiName, 18);
            if (icon != null) btn.setIcon(icon);
        }
        return btn;
    }

    private JPanel buildCenter() {
        java.util.List<ItemMenu> items = user.getItems();

        JPanel leftCol  = buildColumn(items, 0, Math.min(5, items.size()));
        JPanel rightCol = buildColumn(items, 5, items.size());

        JScrollPane leftScroll = new JScrollPane(leftCol);
        leftScroll.setBackground(BG_MAIN);
        leftScroll.getViewport().setBackground(BG_MAIN);
        leftScroll.setBorder(null);
        leftScroll.getVerticalScrollBar().setUnitIncrement(16);

        JScrollPane rightScroll = new JScrollPane(rightCol);
        rightScroll.setBackground(BG_MAIN);
        rightScroll.getViewport().setBackground(BG_MAIN);
        rightScroll.setBorder(null);
        rightScroll.getVerticalScrollBar().setUnitIncrement(16);

        JPanel container = new JPanel(new GridLayout(1, 2, 6, 0));
        container.setBackground(BG_MAIN);
        container.add(leftScroll);
        container.add(rightScroll);
        return container;
    }

    private JPanel buildColumn(java.util.List<ItemMenu> items, int from, int to) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(BG_MAIN);
        panel.setBorder(BorderFactory.createEmptyBorder(8, 6, 8, 6));
        for (int i = from; i < to; i++) {
            ViewCardShop card = new ViewCardShop(items.get(i), user, buyMode, this::atualizarSaldo);
            panel.add(card);
            panel.add(Box.createVerticalStrut(3));
        }
        panel.add(Box.createVerticalGlue());
        return panel;
    }

    public void atualizarSaldo() {
        labelSaldo.setText("$" + ViewCardShop.formatMoney(user.getMoney()));
    }

    private void doPrestige() {
        tycoon.progression.Investors.prestige(user);
        user.reapplyManagers();
        SwingUtilities.invokeLater(() -> {
            atualizarSaldo();
            cardLayout.show(centerContainer, "game");
        });
    }

    public static void abrir() {
        SwingUtilities.invokeLater(() -> new ViewItemMenu().show());
    }
}
