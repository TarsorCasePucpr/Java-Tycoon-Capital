package tycoon.model;

public abstract class ItemMenu {
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

    public abstract void comprar(User user);

    public abstract void receberLucro(User user);
}