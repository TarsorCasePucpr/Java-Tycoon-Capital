// Items Afetados HOCKEYTEAM e LEMON


package tycoon.progression;
import tycoon.progression.Managers;
// Keep earning money even if you are offline — maybe implement, faz sentido?
// Runs the clicking actions for you 
//activar ou desactivar variable gloabl para verificar se tem ativo o seu manager
public class ForestTrump extends Managers {
    private int value_manager = 30000;
    private void action(ItemMenu item){
        if(item instanceof HockeyTeam){
            HockeyTeamManager = true;
        }
        if(item instanceof Lemon){
            LemonManager = true;
        }
    }
}