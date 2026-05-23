package tycoon.business;

public abstract class ItemMenu {
    
    protected int preco_compra;
    protected int quantidade;
    protected int lucro;
    protected int tempo_producao;

    public ItemMenu(int preco_compra, int quantidade, int lucro, int tempo_producao){
        this.preco_compra = preco_compra;
        this.quantidade = quantidade;
        this.lucro = lucro;
        this.tempo_producao = tempo_producao;
    }

    public int getPrecoCompra(){ return this.preco_compra; }
    public int getQuantidade(){ return this.quantidade; }
    public int getLucro(){ return this.lucro * this.quantidade; }
    public int getTempo(){ return this.tempo_producao; }

    public abstract void comprar(User user) throws ExcecaoSaldoInsuficiente;
    public abstract void receberLucro(User user);
    public abstract void reset_cambio_mundo();
}