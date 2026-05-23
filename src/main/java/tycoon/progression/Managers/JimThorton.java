// Items Afetados NEWSPAPERDELIVERY e PIZZADELIVERY


package tycoon.progression;
import tycoon.progression.Managers;
// Keep earning money even if you are offline — maybe implement, faz sentido?
// Runs the clicking actions for you 
public class JimThorton extends Managers {
    private int value_manager = 50000;
    private void action(ItemMenu item){
        if(item instanceof NewspaperDelivery){
            NewspaperDeliveryManager = true;
        }
        if(item instanceof PizzaDelivery){
            PizzaDeliveryManager = true;
        }
    }
}