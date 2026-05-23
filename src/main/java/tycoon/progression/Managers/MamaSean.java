// Items Afetados SHRIMPBOAT


package tycoon.progression;
import tycoon.progression.Managers;
// Keep earning money even if you are offline — maybe implement, faz sentido?
// Runs the clicking actions for you 
public class MamaSean extends Managers {
    private int value_manager = 100000;
    private void action(ItemMenu item){
        if(item instanceof ShrimpBoat){
            ShrimpBoatManager = true;
        }
    }
}