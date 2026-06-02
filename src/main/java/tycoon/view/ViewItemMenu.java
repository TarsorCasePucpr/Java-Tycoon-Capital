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

    private JFrame janela;
    private User user;
    private Game game;
    private JLabel labelSaldo;

    public void show() {
        user = new User();
        game = new Game(user);

        janela = new JFrame("Java Tycoon Capital");
        
        janela.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        janela.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    Persistence.saveUser(user, "user.dat");
                    System.out.println("User salvo em: user.dat");
                } catch (IOException ex) {
                    ex.printStackTrace();
                } finally {
                    janela.dispose();
                    System.exit(0);
                }
            }
        });

        janela.setSize(1280, 720);
        janela.setLayout(new BorderLayout());

        // Painel topo — saldo
        JPanel painelTopo = new JPanel();
        labelSaldo = new JLabel("$0");
        labelSaldo.setFont(new Font("Arial", Font.BOLD, 24));
        painelTopo.add(labelSaldo);
        janela.add(painelTopo, BorderLayout.NORTH);

        // Painel centro — lojas
        JPanel painelCentro = new JPanel();
        painelCentro.add(new JLabel("Lojas virão aqui"));
        janela.add(painelCentro, BorderLayout.CENTER);

        // Painel lateral — menu esquerdo
        JPanel painelLateral = new JPanel();
        painelLateral.setPreferredSize(new Dimension(150, 720));
        painelLateral.setBackground(Color.DARK_GRAY);
        janela.add(painelLateral, BorderLayout.WEST);

        for (ItemMenu item : user.getItems()) {
            painelCentro.add(new ViewCardShop(item));
        }

        janela.setVisible(true);
        game.start();
    }

    public void atualizarSaldo() {
        labelSaldo.setText("$" + user.getMoney());
    }

    public static void abrir() {
        SwingUtilities.invokeLater(() -> new ViewItemMenu().show());
    }
}