package tycoon.business;

public abstract class ItemMenu {
    
    // Preco de compra nao incrementa com a quantidade que uma pessoa compra
    // Em todo momento ele mostra a quantidade que voce pode comprar baseado na quantidade de dinheiro que voce tem
    // Logica da compra de certa quantidade de items com funciona investigar
    
    protected int preco_compra;
    protected int quantidade;
    protected int lucro;
    protected int velocidade_producao;
    protected int tempo_producao;

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
        return this.lucro * this.quantidade;
    }
    public int velocidadeProducao(){
        return this.velocidade_producao;
    }
    public int tempo_producao(){
        return this.tempo_producao;
    }
    public abstract void comprar();
    public abstract void reset_cambio_mundo();
    
}