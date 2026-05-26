package tycoon.model;

import java.util.ArrayList;
import tycoon.business.ItemMenu;
import tycoon.progression.Investors;
import tycoon.progression.Managers;
import tycoon.world.Worlds;

public class User {
    private long globalMoneyQuantity;
    private ArrayList<ItemMenu> items = new ArrayList<>();
    private ArrayList<Investors> investors = new ArrayList<>();
    private ArrayList<Managers> managers = new ArrayList<>();
    private ArrayList<Worlds> currentWorld = new ArrayList<>();
}
