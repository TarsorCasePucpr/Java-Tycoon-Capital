package tycoon.view;

import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JPanel;
import tycoon.model.User;
import tycoon.progression.Unlocks;

public class ViewUnlocksMenu extends JPanel {
    private static final Color BG_MAIN   = new Color(24, 40, 24);
    public ViewUnlocksMenu(User user) {
        // Configurações do painel de menu de desbloqueios
        setBackground(BG_MAIN);
        setLayout(new FlowLayout(FlowLayout.LEFT, 20, 20));
        Unlocks fiatUno = new Unlocks("Fiat Uno com Escada", 10000, "R$10.000", "src/main/resources/emoji/fiat_uno.jpg");
        Unlocks fusca = new Unlocks("Fusca", 20000, "R$20.000", "src/main/resources/emoji/fusca.jpg");
        Unlocks vespa = new Unlocks("Vespa", 22000, "R$22.000", "src/main/resources/emoji/vespa.jpg");
        Unlocks kitnet = new Unlocks("Kitnet", 100000, "R$100.000", "src/main/resources/emoji/kitnet.jpeg");
        Unlocks apartamento = new Unlocks("Apartamento", 500000, "R$500.000", "src/main/resources/emoji/apartamento.jpeg");
        Unlocks carro = new Unlocks("Carro legal", 200000, "R$200.000", "src/main/resources/emoji/carro.jpeg");
        Unlocks casa = new Unlocks("Casa", 1000000, "R$1.000.000", "src/main/resources/emoji/casa.jpeg");
        Unlocks fazenda = new Unlocks("Fazenda", 5000000, "R$5.000.000", "src/main/resources/emoji/fazenda.jpeg");
        Unlocks mansao = new Unlocks("Mansão", 10000000, "R$10.000.000", "src/main/resources/emoji/mansao.jpeg");
        Unlocks yate = new Unlocks("Iate", 50000000, "R$50.000.000", "src/main/resources/emoji/iate.jpeg");
        Unlocks jet = new Unlocks("Jato Privado", 100000000, "R$100.000.000", "src/main/resources/emoji/jet.jpg");
        Unlocks ilha = new Unlocks("Ilha Particular", 200000000, "R$200.000.000", "src/main/resources/emoji/ilha.jpg");

        add(new ViewUnlocks(fiatUno, user));
        add(new ViewUnlocks(fusca, user));
        add(new ViewUnlocks(vespa, user));
        add(new ViewUnlocks(kitnet, user));
        add(new ViewUnlocks(apartamento, user));
        add(new ViewUnlocks(carro, user));
        add(new ViewUnlocks(casa, user));
        add(new ViewUnlocks(fazenda, user));
        add(new ViewUnlocks(mansao, user));
        add(new ViewUnlocks(yate, user));
        add(new ViewUnlocks(jet, user));
        add(new ViewUnlocks(ilha, user));
    }
}
