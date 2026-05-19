package tycoon.model;

public class Lemon extends ItemMenu {

    public Lemon(int preco_compra,int quantidade,int lucro,int velocidade_producao,int tempo_producao){
        super(preco_compra,quantidade,lucro,velocidade_producao,tempo_producao);
    }

    @Override
    public void comprar(User user) throws ExcecaoSaldoInsuficiente {
    if(user.globalmoneyquantity < this.preco_compra){
        throw new ExcecaoSaldoInsuficiente("Saldo insuficiente!");
    }

    int quantidadeCompravel = (int)(user.globalmoneyquantity / this.preco_compra);

    long custoTotal = (long) quantidadeCompravel * this.preco_compra;

    user.globalmoneyquantity -= custoTotal;

    this.quantidade += quantidadeCompravel;

    System.out.println("Comprou "+ quantidadeCompravel+ " barracas de limão");

    System.out.println("Agora possui "+ this.quantidade);
    }

    @Override
    public void receberLucro(User user){

        user.globalmoneyquantity += this.getLucro();

        System.out.println("Recebeu lucro de: "+ this.getLucro());
    }
}