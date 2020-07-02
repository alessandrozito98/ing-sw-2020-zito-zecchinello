package it.polimi.ingsw.connection;

import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.God.*;

/**
 * Used to create a god while initializing the match.
 */

public enum EnumGodCard {
    APOLLO("Your Move: Your Worker may move into an opponent Worker’s space by forcing their Worker to the space yours just vacated."),
    ARTEMIS("Your Move: Your Worker may move one additional time, but not back to its initial space."),
    ATHENA("Opponent’s Turn: If one of your Workers moved up on your last turn, opponent Workers cannot move up this turn."),
    ATLAS("Your Build: Your Worker may build a dome at any level."),
    DEMETER("Your Build: Your Worker may build one additional time, but not on the same space."),
    HEPHAESTUS("Your Build: Your Worker may build one additional block (not dome) on top of your first block."),
    MINOTAUR("Your Move: Your Worker may move into an opponent Worker’s space, if their Worker can be forced one space straight backwards to an unoccupied space at any level."),
    PAN("Win Condition: You also win if your Worker moves down two or more levels."),
    PROMETHEUS("Your Turn: If your Worker does not move up, it may build both before and after moving.");

    public final String description;

    EnumGodCard(String description) {
        this.description = description;
    }

    public God createGod(Game game) {
        switch(this){
            case APOLLO: return new Apollo(game);
            case ARTEMIS: return new Artemis(game);
            case ATHENA: return new Athena(game);
            case ATLAS: return new Atlas(game);
            case DEMETER: return new Demeter(game);
            case HEPHAESTUS: return new Hephaestus(game);
            case MINOTAUR: return new Minotaur(game);
            case PAN: return new Pan(game);
            case PROMETHEUS: return new Prometheus(game);
            default: throw new RuntimeException("Unexpected case!");
        }
    }
}
