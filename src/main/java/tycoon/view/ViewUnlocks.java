package tycoon.view;

import java.awt.Color;
import java.awt.Image;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import tycoon.model.User;
import tycoon.progression.Unlocks;

public class ViewUnlocks extends JPanel{
    private static final Color BG_EXPANDED  = new Color(35, 62, 35);
    public ViewUnlocks(Unlocks item, User user) {
        // Configurações do painel de desbloqueios
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        ImageIcon imagemOriginal = new ImageIcon(item.getImagePath());
        System.out.println(new java.io.File("").getAbsolutePath());
        Image imagemRedimensionada = imagemOriginal.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        JLabel icone = new JLabel(new ImageIcon(imagemRedimensionada));
        JLabel nome = new JLabel(item.getNome());
        JLabel preco = new JLabel(item.getPreco());
        JButton unlockButton = new JButton("Desbloquear");
        icone.setAlignmentX(CENTER_ALIGNMENT);
        nome.setAlignmentX(CENTER_ALIGNMENT);
        preco.setAlignmentX(CENTER_ALIGNMENT);
        unlockButton.setAlignmentX(CENTER_ALIGNMENT);
        add(icone);
        add(nome);
        add(preco);
        add(unlockButton);
        setBackground(BG_EXPANDED);
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        icone.setForeground(Color.WHITE);
        nome.setForeground(Color.WHITE);
        preco.setForeground(Color.WHITE);
        unlockButton.setBackground(Color.decode("#333333"));
        unlockButton.setForeground(Color.WHITE);
        unlockButton.addActionListener(e -> {
            if (user.getMoney() >= item.getPrice() && !item.isUnlocked()) {
                user.setMoney(-item.getPrice());
                item.unlock();
                unlockButton.setText("Desbloqueado");
                unlockButton.setEnabled(false);
            }
            else {
                JOptionPane.showMessageDialog(null, "Você não tem dinheiro suficiente para desbloquear este item!");
            }
        });
    }
}
