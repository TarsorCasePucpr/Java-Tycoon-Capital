package tycoon.model;

public abstract class ItemMenu {
    //preco de compra nao incrementa com a quantidade que uma pessoa compra
    // Em todo momento ele mostra a quantidade que voce pode comprar baseado na quantidade de dinheiro que voce tem
    private abstract int preco_compra;
    private abstract int quantidade;
    private abstract int lucro;
    private abstract int velocidade_producao;
    private abstract int tempo_producao;
    public abstract void comprar();

}
