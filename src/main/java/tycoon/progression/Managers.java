package tycoon.progression;

import tycoon.business.ItemMenu;

public abstract class Managers {
    protected String nome;
    protected int preco;

    public Managers(String nome, int preco) {
        this.nome = nome;
        this.preco = preco;
    }

    public String getNome() {
        return nome;
    }

    public int getPreco() {
        return preco;
    }

    public abstract void action(ItemMenu item);
}
