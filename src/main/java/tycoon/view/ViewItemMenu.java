package tycoon.view;

import javax.swing.*;
import java.awt.Dimension;

public class ViewItemMenu {

    private JFrame janela;

    public void show() {
        janela = new JFrame("Java Tycoon Capital");
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel label = new JLabel("Olá");
        JPanel painel = new JPanel(null);
        painel.add(label);

        Dimension dimensao = label.getPreferredSize();
        label.setBounds(300 / 2, 150 / 2, dimensao.width, dimensao.height);

        /Item1
        //Vai variar baseado na quantidade que o user pode comprar isso vai ter que mudar o tempo todo (Buy x1).
        JButton botao1 = new JButton("Buy x1");
        int larguraBotao = 90;
        int alturaBotao = 30;
        int x = (janela.getWidth() - larguraBotao) / 2;
        int y = (janela.getHeight() - alturaBotao) / 2;
        botao1.setBounds(
            x, y,
            larguraBotao,
            alturaBotao
        );
        painel.add(botao1);

        /Item1
        JButton botao1 = new JButton("Fechar");

        janela.add(painel);
        janela.setSize(300, 150);
        janela.setVisible(true);
    }

    public static void abrir() {
        SwingUtilities.invokeLater(() -> new ViewItemMenu().show());
    }
}


