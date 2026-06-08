package tycoon.view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import tycoon.business.ItemMenu;
import tycoon.model.User;
import tycoon.exceptions.ExcecaoSaldoInsuficiente;

public class ViewCardShop extends JPanel {

    static final Color BG_EXPANDED  = new Color(35, 62, 35);    
    static final Color BG_COMPACT   = new Color(28, 50, 28);    
    static final Color BG_COMPACT2  = new Color(32, 56, 32);
    static final Color BTN_BUY      = new Color(240, 138, 36);  
    static final Color BTN_BUY_OFF  = new Color(60, 60, 60);    
    static final Color TXT_CREAM    = new Color(255, 244, 213); 
    static final Color TXT_GOLD     = new Color(245, 197, 24);  
    static final Color PROG_RUN     = new Color(76, 175, 80);   
    static final Color PROG_DONE    = new Color(245, 197, 24);  

    private final ItemMenu item;
    private final User user;
    private final int[] buyMode;
    private final Runnable onUpdate;
    private boolean expanded;

    private JLabel labelQty;
    private JLabel labelLucro;
    private JLabel labelTimer;
    private JProgressBar progressBar;
    private JButton btnBuy;

    private JButton btnCompactBuy;

    public ViewCardShop(ItemMenu item, User user, int[] buyMode, Runnable onUpdate) {
        this.item = item;
        this.user = user;
        this.buyMode = buyMode;
        this.onUpdate = onUpdate;
        this.expanded = item.getQuantidade() > 0;

        if (expanded) buildExpanded();
        else buildCompact();

        new Timer(100, e -> atualizar()).start();
    }

    private void buildExpanded() {
        removeAll();
        setLayout(new BorderLayout(8, 0));
        setBackground(BG_EXPANDED);
        setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(140, 100, 50)));
        setMaximumSize(new Dimension(Integer.MAX_VALUE, 88));
        setPreferredSize(new Dimension(800, 88));
        setMinimumSize(new Dimension(100, 88));

        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setBackground(new Color(20, 40, 20));  
        leftPanel.setPreferredSize(new Dimension(70, 88));
        leftPanel.setMinimumSize(new Dimension(70, 88));
        leftPanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        leftPanel.setBorder(BorderFactory.createEmptyBorder(8, 4, 8, 4));

        ImageIcon icon = EmojiIcon.get(getEmojiName(), 36);
        JLabel iconLabel = icon != null
                ? new JLabel(icon, SwingConstants.CENTER)
                : new JLabel(getEmojiName(), SwingConstants.CENTER);
        iconLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        leftPanel.add(iconLabel);

        JPanel badgePanel = new JPanel();
        badgePanel.setBackground(new Color(180, 130, 10));  
        badgePanel.setBorder(BorderFactory.createEmptyBorder(1, 6, 1, 6));
        badgePanel.setMaximumSize(new Dimension(60, 22));
        badgePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelQty = new JLabel(String.valueOf(item.getQuantidade()), SwingConstants.CENTER);
        labelQty.setFont(new Font("Arial", Font.BOLD, 13));
        labelQty.setForeground(Color.WHITE);
        badgePanel.add(labelQty);
        leftPanel.add(badgePanel);

        leftPanel.addMouseListener(new MouseAdapter() {
            @Override public void mouseClicked(MouseEvent e) { coletar(); }
            @Override public void mouseEntered(MouseEvent e) { leftPanel.setBackground(new Color(40, 70, 40)); }
            @Override public void mouseExited(MouseEvent e)  { leftPanel.setBackground(new Color(20, 40, 20)); }
        });
        add(leftPanel, BorderLayout.WEST);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBackground(BG_EXPANDED);
        centerPanel.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

        JLabel labelName = new JLabel(item.getNome());
        labelName.setFont(new Font("Arial", Font.BOLD, 13));
        labelName.setForeground(TXT_CREAM);
        centerPanel.add(labelName);

        labelLucro = new JLabel("$" + formatMoney(item.getLucro()) + " / ciclo");
        labelLucro.setFont(new Font("Arial", Font.PLAIN, 11));
        labelLucro.setForeground(new Color(160, 230, 160));
        centerPanel.add(labelLucro);

        centerPanel.add(Box.createVerticalStrut(5));

        progressBar = new JProgressBar(0, 1000);
        progressBar.setValue(0);
        progressBar.setForeground(PROG_RUN);
        progressBar.setBackground(new Color(30, 30, 30));
        progressBar.setBorderPainted(false);
        progressBar.setStringPainted(false);
        progressBar.setMaximumSize(new Dimension(Integer.MAX_VALUE, 18));
        progressBar.setPreferredSize(new Dimension(400, 18));
        progressBar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        progressBar.addMouseListener(new MouseAdapter() {
            @Override public void mouseClicked(MouseEvent e) { coletar(); }
        });
        centerPanel.add(progressBar);

        add(centerPanel, BorderLayout.CENTER);

        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setBackground(BG_EXPANDED);
        rightPanel.setPreferredSize(new Dimension(140, 88));
        rightPanel.setMinimumSize(new Dimension(140, 88));
        rightPanel.setBorder(BorderFactory.createEmptyBorder(8, 4, 8, 8));

        labelTimer = new JLabel("00:00:00", SwingConstants.CENTER);
        labelTimer.setFont(new Font("Courier New", Font.BOLD, 12));
        labelTimer.setForeground(TXT_CREAM);
        labelTimer.setAlignmentX(Component.CENTER_ALIGNMENT);
        rightPanel.add(labelTimer);

        rightPanel.add(Box.createVerticalStrut(6));

        btnBuy = new JButton("Buy ×1");
        btnBuy.setFont(new Font("Arial", Font.BOLD, 11));
        btnBuy.setForeground(Color.WHITE);
        btnBuy.setBackground(BTN_BUY);
        btnBuy.setFocusPainted(false);
        btnBuy.setBorderPainted(false);
        btnBuy.setMaximumSize(new Dimension(130, 30));
        btnBuy.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnBuy.addActionListener(e -> comprar());
        rightPanel.add(btnBuy);

        add(rightPanel, BorderLayout.EAST);

        revalidate();
        repaint();
    }

    private void buildCompact() {
        removeAll();
        setLayout(new BorderLayout(0, 0));
        setBackground(BG_COMPACT);
        setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(80, 60, 35)));
        setMaximumSize(new Dimension(Integer.MAX_VALUE, 55));
        setPreferredSize(new Dimension(800, 55));
        setMinimumSize(new Dimension(100, 55));

        JPanel inner = new JPanel(new BorderLayout(10, 0));
        inner.setBackground(BG_COMPACT);
        inner.setBorder(BorderFactory.createEmptyBorder(6, 12, 6, 10));

        JPanel namePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 4, 0));
        namePanel.setBackground(BG_COMPACT);
        ImageIcon compactIcon = EmojiIcon.get(getEmojiName(), 20);
        if (compactIcon != null) namePanel.add(new JLabel(compactIcon));
        JLabel nameLabel = new JLabel(item.getNome());
        nameLabel.setFont(new Font("Arial", Font.BOLD, 12));
        nameLabel.setForeground(TXT_CREAM);
        namePanel.add(nameLabel);
        inner.add(namePanel, BorderLayout.WEST);

        btnCompactBuy = new JButton("$" + formatMoney(item.getPrecoCompra()));
        btnCompactBuy.setFont(new Font("Arial", Font.BOLD, 11));
        btnCompactBuy.setForeground(Color.WHITE);
        btnCompactBuy.setBackground(BTN_BUY);
        btnCompactBuy.setFocusPainted(false);
        btnCompactBuy.setBorderPainted(false);
        btnCompactBuy.setPreferredSize(new Dimension(120, 30));
        btnCompactBuy.addActionListener(e -> comprar());
        inner.add(btnCompactBuy, BorderLayout.EAST);

        add(inner, BorderLayout.CENTER);

        revalidate();
        repaint();
    }

    private void switchToExpanded() {
        this.expanded = true;
        buildExpanded();
        if (getParent() != null) {
            getParent().revalidate();
            getParent().repaint();
        }
    }

    private void switchToCompact() {
        this.expanded = false;
        buildCompact();
        if (getParent() != null) {
            getParent().revalidate();
            getParent().repaint();
        }
    }

    private void comprar() {
        int n = getBuyAmount();
        if (n <= 0) return; 
        boolean wasZero = item.getQuantidade() == 0;
        try {
            item.comprarN(user, n);
            if (wasZero && item.getQuantidade() > 0) switchToExpanded();
            onUpdate.run();
        } catch (ExcecaoSaldoInsuficiente ex) {
            
        }
    }

    private void coletar() {
        if (item.getQuantidade() <= 0) return;
        if (!item.isReady()) return;
        item.receberLucro(user);
        onUpdate.run();
    }

    private int getBuyAmount() {
        int mode = buyMode[0];
        if (mode == -1) return item.maxAffordable(user.getMoney());
        int affordable = item.maxAffordable(user.getMoney());
        return Math.min(affordable, mode);
    }

    public void atualizar() {
        if (expanded) atualizarExpanded();
        else atualizarCompact();
    }

    private void atualizarExpanded() {
        if (labelQty == null) return;
        if (item.getQuantidade() == 0) { switchToCompact(); return; }

        labelQty.setText(String.valueOf(item.getQuantidade()));
        labelLucro.setText("$" + formatMoney(item.getLucro()) + " / ciclo");

        boolean ready = item.isReady();
        if (ready) {
            
            progressBar.setForeground(PROG_DONE);
            progressBar.setValue(1000);
            labelTimer.setText("00:00");
            labelTimer.setForeground(TXT_GOLD);
        } else {
            progressBar.setForeground(PROG_RUN);
            progressBar.setValue((int)(item.getProgressPercent() * 1000));
            labelTimer.setText(formatTime(item.timeRemainingMs()));
            labelTimer.setForeground(TXT_CREAM);
        }

        int affordable = item.maxAffordable(user.getMoney());
        String modeStr = buyMode[0] == -1 ? "MAX" : "×" + buyMode[0];
        long cost;
        boolean canBuy;
        if (buyMode[0] == -1) {
            int n = Math.max(1, affordable);
            cost = item.calcCostForN(n);
            canBuy = affordable > 0;
        } else {
            cost = item.calcCostForN(buyMode[0]);
            canBuy = affordable >= buyMode[0];
        }
        btnBuy.setText("Buy " + modeStr + "  $" + formatMoney(cost));
        btnBuy.setBackground(canBuy ? BTN_BUY : BTN_BUY_OFF);
    }

    private void atualizarCompact() {
        if (btnCompactBuy == null) return;
        boolean canBuy = user.getMoney() >= item.getPrecoCompra();
        btnCompactBuy.setText("$" + formatMoney(item.getPrecoCompra()));
        btnCompactBuy.setBackground(canBuy ? BTN_BUY : BTN_BUY_OFF);
    }

    private String getEmojiName() {
        switch (item.getNome()) {
            case "Lemon Stand":        return "lemon";
            case "Newspaper Delivery": return "newspaper";
            case "Car Wash":           return "car";
            case "Pizza Delivery":     return "pizza";
            case "Donut Shop":         return "donut";
            case "Shrimp Boat":        return "shrimp";
            case "Hockey Team":        return "hockey";
            default:                   return "briefcase";
        }
    }

    static String formatMoney(long amount) {
        if (amount >= 1_000_000_000_000L) return String.format("%.2fT", amount / 1_000_000_000_000.0);
        if (amount >= 1_000_000_000L)     return String.format("%.2fB", amount / 1_000_000_000.0);
        if (amount >= 1_000_000L)         return String.format("%.2fM", amount / 1_000_000.0);
        if (amount >= 1_000L)             return String.format("%.2fK", amount / 1_000.0);
        return String.valueOf(amount);
    }

    private String formatTime(long ms) {
        if (ms <= 0) return "00:00:00";
        if (ms < 1000) return "0." + (ms / 100) + "s";
        long s = ms / 1000;
        if (s < 60) return s + "s";
        return String.format("%02d:%02d:%02d", s / 3600, (s % 3600) / 60, s % 60);
    }
}
