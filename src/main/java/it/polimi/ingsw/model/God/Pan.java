package it.polimi.ingsw.model.God;

import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.Level;

public class Pan extends God {

    public Pan(Game game){
        super(game);
    }

    @Override
    public boolean winControl(Level oldLevel, Level newLevel) {
        if((oldLevel!=newLevel&&newLevel==Level.LEVEL3)||(newLevel.ordinal()<=oldLevel.ordinal()-2)){
            return true;
        }
        return false;
    }
}
