package tycoon.model;

public abstract class ItemMenu {
    private abstract int preco_compra;
    private abstract int quantidade;
    private abstract int lucro;
    private abstract int velocidade_producao;
    public abstract void comprar();

}
