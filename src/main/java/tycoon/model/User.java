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
    private ArrayList<Worlds> currentWorld = new ArrayList<>();

    public void setMoney(long amount){
        this.globalMoneyQuantity += amount;
    }

    public long getMoney(){
        return globalMoneyQuantity;
    }
    
}
