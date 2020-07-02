package it.polimi.ingsw.model.God;

import it.polimi.ingsw.model.Cell;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.Level;
import it.polimi.ingsw.model.Worker;

public class Atlas extends God {

    public Atlas(Game game){
        super(game);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isFeasibleBuild(Cell cell, Worker worker, Level level) {

        if(cell.getWorker() != null) {
            return false;
        }

        if(cell.getLevel() == Level.DOME) {
            return false;
        }

        if(!(cell.getLevel().ordinal() == (level.ordinal() - 1)) && !(level == Level.DOME)) {
            return false;
        }

        if(cell.getX() == worker.getPosition().getX() - 1 || cell.getX() == worker.getPosition().getX() + 1 || cell.getX() == worker.getPosition().getX()) {
            return cell.getY() == worker.getPosition().getY() - 1 || cell.getY() == worker.getPosition().getY() + 1 || cell.getY() == worker.getPosition().getY();
        }
        return false;
    }
}
