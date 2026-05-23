package tycoon.model;

import tycoon.business.ExcecaoSaldoInsuficiente;
import tycoon.business.ItemMenu;
import tycoon.business.User;

public class ShrimpBoat extends ItemMenu {

    private static final int PRECO_COMPRA       = 622080;
    private static final int QUANTIDADE_INICIAL = 0;
    private static final int LUCRO              = 87000;
    private static final int TEMPO_PRODUCAO     = 96;

    // Construtor sem argumentos — valores já definidos
    public ShrimpBoat() {
        super(PRECO_COMPRA, QUANTIDADE_INICIAL, LUCRO, TEMPO_PRODUCAO);
    }

    @Override
    public void comprar(User user) throws ExcecaoSaldoInsuficiente {
        if (user.globalmoneyquantity < this.preco_compra) {
            throw new ExcecaoSaldoInsuficiente("Saldo insuficiente!");
        }

        int quantidadeCompravel = (int) (user.globalmoneyquantity / this.preco_compra);
        long custoTotal = (long) quantidadeCompravel * this.preco_compra;

        user.globalmoneyquantity -= custoTotal;
        this.quantidade += quantidadeCompravel;

        System.out.println("Comprou " + quantidadeCompravel + " barcos de camarão");
        System.out.println("Agora possui " + this.quantidade);
    }

    @Override
    public void receberLucro(User user) {
        user.globalmoneyquantity += this.getLucro();
        System.out.println("Recebeu lucro de: " + this.getLucro());
    }

    @Override
    public void reset_cambio_mundo() {
        this.quantidade = 0;
    }
}