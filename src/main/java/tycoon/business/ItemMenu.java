package tycoon.business;

import tycoon.exceptions.ExcecaoSaldoInsuficiente;
import tycoon.model.User;

public abstract class ItemMenu {

    protected int preco_compra;
    protected int quantidade;
    protected int lucro;
    protected int tempo_producao;
    protected long nextReadyTime;
    protected boolean managerAtivo = false;

    public ItemMenu(int preco_compra, int quantidade, int lucro, int tempo_producao) {
        this.preco_compra = preco_compra;
        this.quantidade = quantidade;
        this.lucro = lucro;
        this.tempo_producao = tempo_producao;
        this.nextReadyTime = 0;
    }

    public int getPrecoCompra() { return this.preco_compra; }
    public int getQuantidade() { return this.quantidade; }
    public int getLucro() { return this.lucro * this.quantidade; }
    public int getTempo() { return this.tempo_producao; }
    public boolean isManagerAtivo() { return this.managerAtivo; }

    public void setManager(boolean ativo) {
        this.managerAtivo = ativo;
    }

    public void startProduction() {
        if (this.quantidade <= 0) return;
        this.nextReadyTime = System.currentTimeMillis() + this.tempo_producao * 1000L;
    }

    public void ensureProductionStarted() {
        if (this.quantidade > 0 && this.nextReadyTime == 0) {
            startProduction();
        }
    }

    public boolean isReady() {
        return this.quantidade > 0 && this.nextReadyTime != 0 && System.currentTimeMillis() >= this.nextReadyTime;
    }

    public long timeRemainingSeconds() {
        if (this.quantidade <= 0 || this.nextReadyTime == 0) {
            return this.tempo_producao;
        }
        long millis = this.nextReadyTime - System.currentTimeMillis();
        return millis <= 0 ? 0 : (millis + 999) / 1000;
    }

    public void receberLucro(User user) {
        if (this.quantidade <= 0) {
            System.out.println("Ainda não há unidades compradas para produzir.");
            return;
        }

        if (!isReady()) {
            if (!managerAtivo) {
                System.out.println("Ainda não pronto. Falta " + timeRemainingSeconds() + " segundos.");
            }
            return;
        }

        user.setMoney(this.getLucro());
        System.out.println("Recebeu lucro de: " + this.getLucro());
        startProduction();
    }
//implementar automatização
    public void tickManager(User user) {
        if (this.managerAtivo) {
            receberLucro(user);
        }
    }

    public abstract void comprar(User user) throws ExcecaoSaldoInsuficiente;

    public abstract void reset_cambio_mundo();
}
