package tycoon.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import tycoon.model.User;

public class ViewUpgrades extends JPanel {

    private static final Color BG_MAIN   = new Color(24, 40, 24);
    private static final Color BG_CARD   = new Color(35, 55, 35);
    private static final Color BTN_BUY   = new Color(240, 138, 36);
    private static final Color BTN_OFF   = new Color(60, 60, 60);
    private static final Color TXT_CREAM = new Color(255, 244, 213);
    private static final Color TXT_GOLD  = new Color(245, 197, 24);
    private static final Color TXT_GREEN = new Color(130, 220, 130);

    private User user;
    private JButton btnUpgrade;
    private JLabel lblStatus;

    public ViewUpgrades(User user) {
        this.user = user;
        setLayout(new BorderLayout());
        setBackground(BG_MAIN);
        add(buildHeader(), BorderLayout.NORTH);
        add(buildCenter(), BorderLayout.CENTER);
    }

    private JPanel buildHeader() {
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(15, 30, 15));
        header.setBorder(BorderFactory.createEmptyBorder(10, 16, 10, 16));
        header.setPreferredSize(new Dimension(0, 52));

        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        titlePanel.setBackground(new Color(15, 30, 15));
        JLabel title = new JLabel("💰 Upgrades");
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setForeground(TXT_GOLD);
        titlePanel.add(title);
        header.add(titlePanel, BorderLayout.WEST);

        JLabel subtitle = new JLabel("Potencialize seus ganhos com bônus especiais");
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

        panel.add(buildUpgradeCard());
        panel.add(Box.createVerticalGlue());

        return panel;
    }

    private JPanel buildUpgradeCard() {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(BG_CARD);
        card.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        card.setMaximumSize(new Dimension(400, 250));

        JLabel titleUp = new JLabel("Multiplicador x2");
        titleUp.setFont(new Font("Arial", Font.BOLD, 18));
        titleUp.setForeground(TXT_GOLD);
        titleUp.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(titleUp);

        card.add(Box.createVerticalStrut(8));

        JLabel descLabel = new JLabel("Receba x2 de saldo por 5 minutos");
        descLabel.setFont(new Font("Arial", Font.PLAIN, 13));
        descLabel.setForeground(TXT_CREAM);
        descLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(descLabel);

        card.add(Box.createVerticalStrut(16));

        JLabel costLabel = new JLabel("Custo: 50.000");
        costLabel.setFont(new Font("Arial", Font.BOLD, 14));
        costLabel.setForeground(new Color(255, 140, 120));
        costLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(costLabel);

        card.add(Box.createVerticalStrut(16));

        lblStatus = new JLabel("");
        lblStatus.setFont(new Font("Arial", Font.ITALIC, 12));
        lblStatus.setForeground(TXT_GREEN);
        lblStatus.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(lblStatus);

        card.add(Box.createVerticalStrut(16));

        btnUpgrade = new JButton("Comprar");
        btnUpgrade.setFont(new Font("Arial", Font.BOLD, 14));
        btnUpgrade.setBackground(BTN_BUY);
        btnUpgrade.setForeground(Color.WHITE);
        btnUpgrade.setFocusPainted(false);
        btnUpgrade.setBorderPainted(false);
        btnUpgrade.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnUpgrade.setMaximumSize(new Dimension(140, 45));
        btnUpgrade.setPreferredSize(new Dimension(140, 45));
        btnUpgrade.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                comprarUpgrade();
            }
        });
        card.add(btnUpgrade);

        return card;
    }

    private void comprarUpgrade() {
        if (user.getMoney() < 50000) {
            JOptionPane.showMessageDialog(this, "Saldo insuficiente!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        user.setMoney(-50000);
        user.setMultiplicador(2);

        JOptionPane.showMessageDialog(this, "X2 ativado por 5 minutos!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        btnUpgrade.setEnabled(false);
        btnUpgrade.setBackground(BTN_OFF);

        Timer timer = new Timer(300000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                user.setMultiplicador(1);
                btnUpgrade.setEnabled(true);
                btnUpgrade.setBackground(BTN_BUY);
                lblStatus.setText("");
                JOptionPane.showMessageDialog(ViewUpgrades.this, "O bônus X2 acabou!", "Info", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        timer.setRepeats(false);
        timer.start();

        lblStatus.setText("Ativo por 5 minutos");
    }
}
