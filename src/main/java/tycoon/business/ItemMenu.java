package tycoon.business;

import java.io.Serializable;
import tycoon.exceptions.ExcecaoSaldoInsuficiente;
import tycoon.model.User;

public abstract class ItemMenu implements Serializable {
    private static final long serialVersionUID = 1L;
    protected String nome;
    protected long preco_compra_base;
    protected int quantidade;
    protected long lucro;
    protected long tempo_producao_ms;   
    protected long nextReadyTime;
    protected boolean managerAtivo = false;
    protected double priceCoefficient;

    private static final int[] HALVING_MILESTONES = {25, 50, 100, 200, 300, 400};

    public ItemMenu(String nome, long preco_compra_base, int quantidade, long lucro, long tempo_producao_ms, double priceCoefficient) {
        this.nome = nome;
        this.preco_compra_base = preco_compra_base;
        this.quantidade = quantidade;
        this.lucro = lucro;
        this.tempo_producao_ms = tempo_producao_ms;
        this.nextReadyTime = 0;
        this.priceCoefficient = priceCoefficient;
    }

    public String getNome() { return this.nome; }
    public int getQuantidade() { return this.quantidade; }
    public long getLucro() { return this.lucro * this.quantidade; }
    public long getTempoBaseMs() { return this.tempo_producao_ms; }
    public boolean isManagerAtivo() { return this.managerAtivo; }

    public long getPrecoCompra() {
        return Math.max(1L, Math.round(preco_compra_base * Math.pow(priceCoefficient, quantidade)));
    }

    public void setManager(boolean ativo) { this.managerAtivo = ativo; }

    private int calcHalvings() {
        int halvings = 0;
        for (int m : HALVING_MILESTONES) {
            if (this.quantidade >= m) halvings++;
        }
        return halvings;
    }

    public long getTempoAtualMs() {
        
        return Math.max(100L, tempo_producao_ms >> calcHalvings());
    }

    public void startProduction() {
        if (this.quantidade <= 0) return;
        this.nextReadyTime = System.currentTimeMillis() + getTempoAtualMs();
    }

    public void ensureProductionStarted() {
        if (this.quantidade > 0 && this.nextReadyTime == 0) startProduction();
    }

    public boolean isReady() {
        return this.quantidade > 0 && this.nextReadyTime != 0 && System.currentTimeMillis() >= this.nextReadyTime;
    }

    public long timeRemainingMs() {
        if (this.quantidade <= 0 || this.nextReadyTime == 0) return getTempoAtualMs();
        long ms = this.nextReadyTime - System.currentTimeMillis();
        return ms <= 0 ? 0 : ms;
    }

    public long timeRemainingSeconds() {
        long ms = timeRemainingMs();
        return ms == 0 ? 0 : (ms + 999) / 1000;
    }

    public double getProgressPercent() {
        if (quantidade <= 0 || nextReadyTime == 0) return 0.0;
        long totalMs = getTempoAtualMs();
        return Math.min(1.0, (double)(totalMs - timeRemainingMs()) / totalMs);
    }

    public void receberLucro(User user) {
        if (this.quantidade <= 0 || !isReady()) return;
        long lucroTotal = (long)(this.getLucro() * user.getInvestorMultiplier());
        user.setMoney(lucroTotal);
        user.addLifetimeEarnings(lucroTotal);
        startProduction();
    }

    public void tickManager(User user) {
        if (this.managerAtivo) receberLucro(user);
    }

    public long calcCostForN(int n) {
        if (n <= 0) return 0;
        long total = 0;
        for (int i = 0; i < n; i++) {
            long price = Math.max(1L, Math.round(preco_compra_base * Math.pow(priceCoefficient, quantidade + i)));
            total += price;
            if (total < 0) return Long.MAX_VALUE;
        }
        return total;
    }

    public int maxAffordable(long money) {
        long remaining = money;
        int n = 0;
        while (n < 100000) {
            long price = Math.max(1L, Math.round(preco_compra_base * Math.pow(priceCoefficient, quantidade + n)));
            if (remaining < price) break;
            remaining -= price;
            n++;
        }
        return n;
    }

    public void comprarN(User user, int n) throws ExcecaoSaldoInsuficiente {
        if (n <= 0) return;
        long custo = calcCostForN(n);
        if (custo == Long.MAX_VALUE || user.getMoney() < custo) {
            throw new ExcecaoSaldoInsuficiente("Saldo insuficiente!");
        }
        user.setMoney(-custo);
        this.quantidade += n;
        ensureProductionStarted();
    }

    public abstract void comprar(User user) throws ExcecaoSaldoInsuficiente;
    public abstract void reset_cambio_mundo();
}
