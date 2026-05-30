package tycoon.model;

import java.io.Serializable;
import java.util.ArrayList;
import tycoon.business.ItemMenu;
import tycoon.progression.Investors;
import tycoon.progression.Managers;
import tycoon.world.Worlds;

public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    private long globalMoneyQuantity;
    private ArrayList<ItemMenu> items = new ArrayList<>();
    private ArrayList<Investors> investors = new ArrayList<>();
    private ArrayList<Managers> managers = new ArrayList<>();
    private Worlds currentWorld;

    public void setMoney(long amount) {
        this.globalMoneyQuantity += amount;
    }

    public long getMoney() {
        return globalMoneyQuantity;
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
