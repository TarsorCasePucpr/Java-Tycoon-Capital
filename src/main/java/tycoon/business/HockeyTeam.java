package tycoon.business;

import tycoon.exceptions.ExcecaoSaldoInsuficiente;
import tycoon.model.User;

public class HockeyTeam extends ItemMenu {

    private static final int PRECO_COMPRA       = 7464960;
    private static final int QUANTIDADE_INICIAL = 0;
    private static final int LUCRO              = 787000;
    private static final int TEMPO_PRODUCAO     = 384;

    public HockeyTeam() {
        super(PRECO_COMPRA, QUANTIDADE_INICIAL, LUCRO, TEMPO_PRODUCAO);
    }

    public void ativarHockeyTeamManager() {
        this.setManager(true);
    }

    @Override
    public void comprar(User user) throws ExcecaoSaldoInsuficiente {
        if (user.getMoney() < this.preco_compra) {
            throw new ExcecaoSaldoInsuficiente("Saldo insuficiente!");
        }

        int quantidadeCompravel = (int) (user.getMoney() / this.preco_compra);
        long custoTotal = (long) quantidadeCompravel * this.preco_compra;

        user.setMoney(-custoTotal);

        this.quantidade += quantidadeCompravel;
        ensureProductionStarted();
        System.out.println("Comprou " + quantidadeCompravel + " Times de hockey");
        System.out.println("Agora possui " + this.quantidade);
    }

    @Override
    public void receberLucro(User user) {
        super.receberLucro(user);
    }

    @Override
    public void reset_cambio_mundo() {
        this.quantidade = 0;
        this.nextReadyTime = 0;
        this.managerAtivo = false;
    }
}