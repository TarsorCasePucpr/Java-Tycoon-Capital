package tycoon.progression;

// Keep earning money even if you are offline — maybe implement, faz sentido?
// Runs the clicking actions for you 
public abstract class Managers {
    private int value_manager;
    private abstract void action(ItemMenu item);
}
