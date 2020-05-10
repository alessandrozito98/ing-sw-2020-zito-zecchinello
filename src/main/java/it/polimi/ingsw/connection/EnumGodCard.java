package it.polimi.ingsw.connection;

import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.God.*;

public enum EnumGodCard {
    APOLLO,
    ARTEMIS,
    ATHENA,
    ATLAS,
    DEMETER,
    HEPHAESTUS,
    MINOTAUR,
    PAN,
    PROMETHEUS;

    public God createGod(Game game){
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
