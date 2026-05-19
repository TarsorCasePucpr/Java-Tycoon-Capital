package tycoon.model;

public abstract class ItemMenu {
    // Preco de compra nao incrementa com a quantidade que uma pessoa compra
    // Em todo momento ele mostra a quantidade que voce pode comprar baseado na quantidade de dinheiro que voce tem
    private abstract int preco_compra;
    private abstract int quantidade;
    private abstract int lucro;
    private abstract int velocidade_producao;
    private abstract int tempo_producao;
    public ItemMenu(int preco_compra,int quantidade,int lucro,int velocidade_producao,int tempo_producao){
        this.preco_compra = preco_compra;
        this.quantidade = quantidade;
        this.lucro = lucro;
        this.velocidade_producao = velocidade_producao;
        this.tempo_producao = tempo_producao;
    }
    public int getPrecoCompra(){
        return this.preco_compra;
    }
    public int getQuantidade(){
        return this.quantidade;
    }
    public int getLucro(){
        return this.lucro;
    }
    public int velocidadeProducao(){
        return this.velocidade_producao;
    }
    public int tempo_producao(){
        return this.tempo_producao;
    }
    public abstract void comprar();
}
