package tycoon.model;

import tycoon.business.ExcecaoSaldoInsuficiente;
import tycoon.business.ItemMenu;
import tycoon.business.User;

public class CarWash extends ItemMenu {


    public boolean CarWashManager = false;

    //Construtor CarWash reutiliza o construtor do ItemMenu

    private static final int PRECO_COMPRA       = 540;
    private static final int QUANTIDADE_INICIAL = 0;
    private static final int LUCRO              = 70;
    private static final int TEMPO_PRODUCAO     = 6;

    // Construtor sem argumentos — valores já definidos
    public CarWash() {
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

        System.out.println("Comprou " + quantidadeCompravel + " Lava-rápidos");
        System.out.println("Agora possui " + this.quantidade);
    }

    @Override

    //Metodo receber lucro do item (Atauliza o dinheiro do usuario sumando a quantidade gerada do item em um periodo de tempo ate o click do User ou ---Manager---)
    public void receberLucro(User user){

        // Atualizar o dinheiro do usuario sumando a quantidade gerada do item
        if (CarWashManager){
            // Evento de click para que o manager consiga fazer por conta do user 
            user.globalmoneyquantity += this.getLucro();
        }else{
            user.globalmoneyquantity += this.getLucro();
        }

        //Print :-) ------- (Mudar quando implementarmos a intereface) -------
        System.out.println("Recebeu lucro de: "+ this.getLucro());

    }

    @Override
    public void reset_cambio_mundo() {
        this.quantidade = 0;
    }
}