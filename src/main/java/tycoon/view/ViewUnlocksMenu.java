    package tycoon.view;

    import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JPanel;
    import tycoon.progression.Unlocks;

    public class ViewUnlocksMenu extends JPanel {
        private static final Color BG_MAIN   = new Color(24, 40, 24);
        public ViewUnlocksMenu() {
            // Configurações do painel de menu de desbloqueios
            setBackground(BG_MAIN);
            setLayout(new FlowLayout(FlowLayout.LEFT, 20, 20));
            Unlocks fiatUno = new Unlocks("Fiat Uno com Escada", 10000, "src/main/resources/emoji/fiat_uno.jpg");
            Unlocks fusca = new Unlocks("Fusca", 20000, "src/main/resources/emoji/fusca.jpg");
            Unlocks vespa = new Unlocks("Vespa", 22000, "src/main/resources/emoji/vespa.jpg");
            Unlocks kitnet = new Unlocks("Kitnet", 100000, "src/main/resources/emoji/kitnet.jpeg");
            Unlocks apartamento = new Unlocks("Apartamento", 500000, "src/main/resources/emoji/apartamento.jpeg");
            Unlocks carro = new Unlocks("Carro legal", 200000, "src/main/resources/emoji/carro.jpg");
            Unlocks casa = new Unlocks("Casa", 1000000, "src/main/resources/emoji/casa.jpg");
            Unlocks fazenda = new Unlocks("Fazenda", 5000000, "src/main/resources/emoji/fazenda.jpg");
            Unlocks mansao = new Unlocks("Mansão", 10000000, "src/main/resources/emoji/mansao.jpg");
            Unlocks yate = new Unlocks("Iate", 50000000, "src/main/resources/emoji/yate.jpg");
            Unlocks jet = new Unlocks("Jato Privado", 100000000, "src/main/resources/emoji/jet.jpg");
            Unlocks ilha = new Unlocks("Ilha Particular", 200000000, "src/main/resources/emoji/ilha.jpg");
            add(new ViewUnlocks(fiatUno));
            add(new ViewUnlocks(fusca));
            add(new ViewUnlocks(vespa));
            add(new ViewUnlocks(kitnet));
            add(new ViewUnlocks(apartamento));
            add(new ViewUnlocks(carro));
            add(new ViewUnlocks(casa));
            add(new ViewUnlocks(fazenda));
            add(new ViewUnlocks(mansao));
            add(new ViewUnlocks(yate));
            add(new ViewUnlocks(jet));
            add(new ViewUnlocks(ilha));
        }
    }
