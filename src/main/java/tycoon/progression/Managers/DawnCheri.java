// Items Afetados CARWASH e DONUTSHOP


package tycoon.progression;
import tycoon.progression.Managers;
// Keep earning money even if you are offline — maybe implement, faz sentido?
// Runs the clicking actions for you 
public class DawnCheri extends Managers {
    private int value_manager = 10000;
    private void action(ItemMenu item){
        if(item instanceof CarWash){
            CarWashManager = true;
        }
        if(item instanceof DonutShop){
            DonutShopManager = true;
        }
    }
}