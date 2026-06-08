package tycoon.model;

import java.io.Serializable;
import java.util.ArrayList;
import tycoon.business.ItemMenu;
import tycoon.progression.Investors;
import tycoon.progression.Managers;
import tycoon.world.Worlds;

public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final long STARTING_MONEY = 20;
    private long globalMoneyQuantity = STARTING_MONEY;
    private long lifetimeEarnings = 0;
    private ArrayList<ItemMenu> items = new ArrayList<>();
    private ArrayList<Investors> investors = new ArrayList<>();
    private ArrayList<Managers> managers = new ArrayList<>();
    private Worlds currentWorld;

    public void addMoney(long amount) {
        this.globalMoneyQuantity += amount;
    }

    public void setMoney(long delta) {
        this.globalMoneyQuantity += delta;
    }

    public void resetMoney() {
        this.globalMoneyQuantity = STARTING_MONEY;
    }

    public long getMoney() {
        return globalMoneyQuantity;
    }

    public void addLifetimeEarnings(long amount) {
        this.lifetimeEarnings += amount;
    }

    public long getLifetimeEarnings() {
        return lifetimeEarnings;
    }

    public void addInvestor(Investors investor) {
        this.investors.add(investor);
    }

    public double getInvestorMultiplier() {
        return 1.0 + Investors.calculateMultiplier(investors.size());
    }

    public void addManager(Managers m) {
        this.managers.add(m);
        for (ItemMenu item : items) {
            m.action(item);
        }
    }

    public void reapplyManagers() {
        for (Managers m : managers) {
            for (ItemMenu item : items) {
                m.action(item);
            }
        }
    }

    public void addItem(ItemMenu item) {
        this.items.add(item);
    }

    public ArrayList<ItemMenu> getItems() {
        return items;
    }

    public ArrayList<Investors> getInvestors() {
        return investors;
    }

    public ArrayList<Managers> getManagers() {
        return managers;
    }

    public Worlds getCurrentWorld() {
        return currentWorld;
    }

    public void setCurrentWorld(Worlds world) {
        this.currentWorld = world;
    }

}
