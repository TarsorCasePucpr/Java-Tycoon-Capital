package tycoon.business;

import tycoon.exceptions.ExcecaoSaldoInsuficiente;
import tycoon.model.User;

public class DonutShop extends ItemMenu {

    private static final int PRECO_COMPRA       = 51840;
    private static final int QUANTIDADE_INICIAL = 0;
    private static final int LUCRO              = 9000;
    private static final int TEMPO_PRODUCAO     = 24;

    public DonutShop() {
        super("Donut Shop", PRECO_COMPRA, QUANTIDADE_INICIAL, LUCRO, TEMPO_PRODUCAO);
    }

    public void ativarDonutShopManager() {
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
        System.out.println("Comprou " + quantidadeCompravel + " Donut's shops");
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