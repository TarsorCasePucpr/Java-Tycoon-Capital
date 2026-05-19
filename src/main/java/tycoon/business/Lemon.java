package tycoon.model;

import tycoon.business.ItemMenu;

public class Lemon extends ItemMenu {
    public Lemon(int preco_compra,int quantidade,int lucro,int velocidade_producao,int tempo_producao){
        super(preco_compra,quantidade,lucro,velocidade_producao,tempo_producao);
    }
    public void comprar(){
        //double "saldo" (do usuario) -= this.getPrecoCompra();
    }
}
