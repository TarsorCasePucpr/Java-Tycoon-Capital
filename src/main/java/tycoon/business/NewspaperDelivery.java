package tycoon.business;

import tycoon.business.ItemMenu;

public class NewspaperDelivery extends ItemMenu {
    //Construtor CarWash reutiliza o construtor do ItemMenu
    public NewspaperDelivery(int preco_compra,int quantidade,int lucro,int velocidade_producao,int tempo_producao){
        super(preco_compra,quantidade,lucro,velocidade_producao,tempo_producao);
    }

    @Override
    //Metodo compra do item com ExcecaoSaldoInsuficiente
    public void comprar(User user) throws ExcecaoSaldoInsuficiente {

        //Verificação de saldo insuficiente -> ExcecaoSaldoInsuficiente
        if(user.globalmoneyquantity < this.preco_compra){
            throw new ExcecaoSaldoInsuficiente("Saldo insuficiente!");
        }

        //Calculo da quantidade que pode ser comprada do item (O calculo tem que ser feito o tempo todo em bucle)
        int quantidadeCompravel = (int)(user.globalmoneyquantity / this.preco_compra);

        //Calculo de quantos é o custo de todos os item (O calculo tem que ser feito o tempo todo em bucle)
        long custoTotal = (long) quantidadeCompravel * this.preco_compra;

        //Subtraímos o custo da compra dos items do valor geral que o user têm
        user.globalmoneyquantity -= custoTotal;

        //Atualizamos a quantidade de items que o user possui
        this.quantidade += quantidadeCompravel;

        //Print :-) ------- (Mudar quando implementarmos a intereface) -------
        System.out.println("Comprou "+ quantidadeCompravel+ " de lava-rápidos");

        //Print :-) ------- (Mudar quando implementarmos a intereface) -------
        System.out.println("Agora possui "+ this.quantidade);
    }

    @Override
    //Metodo receber lucro do item (Atauliza o dinheiro do usuario sumando a quantidade gerada do item em um periodo de tempo ate o click do User ou ---Manager---)
    public void receberLucro(User user){

        // Atualizar o dinheiro do usuario sumando a quantidade gerada do item
        user.globalmoneyquantity += this.getLucro();

        //Print :-) ------- (Mudar quando implementarmos a intereface) -------
        System.out.println("Recebeu lucro de: "+ this.getLucro());
    }
}
