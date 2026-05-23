package tycoon.model;

import tycoon.business.ExcecaoSaldoInsuficiente;
import tycoon.business.ItemMenu;
import tycoon.business.User;

public class PizzaDelivery extends ItemMenu {

<<<<<<< HEAD
    public boolean PizzaDeliveryManager = false;

    //Construtor CarWash reutiliza o construtor do ItemMenu
    public PizzaDelivery(int preco_compra,int quantidade,int lucro,int velocidade_producao,int tempo_producao){
        super(preco_compra,quantidade,lucro,velocidade_producao,tempo_producao);
=======
    private static final int PRECO_COMPRA       = 4320;
    private static final int QUANTIDADE_INICIAL = 0;
    private static final int LUCRO              = 1600;
    private static final int TEMPO_PRODUCAO     = 12;

    // Construtor sem argumentos — valores já definidos
    public PizzaDelivery() {
        super(PRECO_COMPRA, QUANTIDADE_INICIAL, LUCRO, TEMPO_PRODUCAO);
>>>>>>> 6206f6edbe0878115ba2865ccefc69f14177fd60
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

        System.out.println("Comprou " + quantidadeCompravel + " deliveries");
        System.out.println("Agora possui " + this.quantidade);
    }

    @Override
<<<<<<< HEAD
    //Metodo receber lucro do item (Atauliza o dinheiro do usuario sumando a quantidade gerada do item em um periodo de tempo ate o click do User ou ---Manager---)
    public void receberLucro(User user){

        // Atualizar o dinheiro do usuario sumando a quantidade gerada do item
        if (PizzaDeliveryManager){
            // Evento de click para que o manager consiga fazer por conta do user 
            user.globalmoneyquantity += this.getLucro();
        }else{
            user.globalmoneyquantity += this.getLucro();
        }

        //Print :-) ------- (Mudar quando implementarmos a intereface) -------
        System.out.println("Recebeu lucro de: "+ this.getLucro());
=======
    public void receberLucro(User user) {
        user.globalmoneyquantity += this.getLucro();
        System.out.println("Recebeu lucro de: " + this.getLucro());
>>>>>>> 6206f6edbe0878115ba2865ccefc69f14177fd60
    }

    @Override
    public void reset_cambio_mundo() {
        this.quantidade = 0;
    }
}