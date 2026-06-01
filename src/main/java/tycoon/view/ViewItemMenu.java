package tycoon.view;

import java.awt.Dimension;
import javax.swing.*;
import tycoon.Game;
import tycoon.model.User;

public class ViewItemMenu {

    private JFrame janela;

    public void show() {
        User user = new User();
        Game game = new Game(user);
        game.start();

        janela = new JFrame("Java Tycoon Capital");
        janela.setSize(300, 150);
        janela = new JFrame("Java Tycoon Capital");
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel label = new JLabel("Olá");
        JPanel painel = new JPanel(null);
        painel.add(label);

        Dimension dimensao = label.getPreferredSize();
        label.setBounds(300 / 2, 150 / 2, dimensao.width, dimensao.height);

        // Item1
        // Vai variar baseado na quantidade que o user pode comprar isso vai ter que
        // mudar o tempo todo (Buy x1).
        JButton botao1 = new JButton("Buy x1");
        int larguraBotao = 90;
        int alturaBotao = 30;
        int x = (janela.getWidth() - larguraBotao) / 2;
        int y = (janela.getHeight() - alturaBotao) / 2;
        botao1.setBounds(
                x, y,
                larguraBotao,
                alturaBotao);
        painel.add(botao1);

        // Item1
        JButton botaoFechar = new JButton("Fechar");

        janela.add(painel);
        janela.setSize(300, 150);
        janela.setVisible(true);
    }

    public static void abrir() {
        SwingUtilities.invokeLater(() -> new ViewItemMenu().show());
    }
}
