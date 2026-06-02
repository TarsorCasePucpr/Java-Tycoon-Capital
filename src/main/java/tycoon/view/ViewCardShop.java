package tycoon.view;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import tycoon.business.ItemMenu;

public class ViewCardShop extends JPanel{
    public ViewCardShop(ItemMenu item) {
        JLabel labelSaldo = new JLabel("Saldo: $1000");

        // Configurações do painel
        setLayout(new BorderLayout());
        
        // Painel topo — saldo
        JPanel itemNome = new JPanel();
        itemNome.add(new JLabel(item.getNome()));
        add(itemNome, BorderLayout.CENTER);
        
    }
}