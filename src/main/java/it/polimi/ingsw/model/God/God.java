package it.polimi.ingsw.model.God;

import it.polimi.ingsw.model.*;

/**
 * Represents a God with normal characteristics and normal rules.
 */

public abstract class God {
    protected int availableMoveNumber;
    protected int availableBuildNumber;
    protected boolean canMoveUp;
    protected Board board;
    protected boolean hasMoved;
    protected boolean hasBuilt;

    public God(Game game){
        this.availableMoveNumber = 1;
        this.availableBuildNumber = 0;
        this.canMoveUp = true;
        this.board = game.getBoard();
        this.hasMoved = false;
        this.hasBuilt = false;
    }

    /**
     * Moves a worker to a new position.
     * @param cell
     * The new position.
     * @param worker
     * The worker that has to be moved.
     */
    public void move(Cell cell, Worker worker){
        worker.getPosition().removeWorker();
        cell.addWorker(worker);
        worker.setPosition(cell);
        setAvailableMoveNumber(this.getAvailableMoveNumber()-1);
        setAvailableBuildNumber(this.getAvailableBuildNumber()+1);
        setHasMoved(true);
    }
    /**
     * Build a new level in a cell.
     * @param cell
     * The cell where it has to be placed the level.
     * @param worker
     * The worker that has to build.
     * @param level
     * The level that has to be built.
     */
    public void build(Cell cell, Worker worker, Level level){
        cell.setLevel(level);
        setAvailableBuildNumber(this.getAvailableBuildNumber()-1);
        setHasBuilt(true);
        setAvailableMoveNumber(0);
    }

    /**
     * Checks if a worker can be moved to a new position.
     * @param cell
     * The new position.
     * @param worker
     * The worker that has to be moved.
     */
    public boolean isFeasibleMove(Cell cell,Worker worker){

        if(cell.getWorker()!=null) {
            return false;
        }

        if(cell.getLevel()==Level.DOME){
            return false;
        }

        if(cell.getLevel().ordinal()>(worker.getPosition().getLevel().ordinal()+1)){
            return false;
        }

        if(!this.getCanMoveUp()&&(cell.getLevel().ordinal()==(worker.getPosition().getLevel().ordinal()+1))){
            return false;
        }

        if(cell.getX() == worker.getPosition().getX() - 1 || cell.getX() == worker.getPosition().getX() + 1 || cell.getX() == worker.getPosition().getX()) {
            if (cell.getY() == worker.getPosition().getY() - 1 || cell.getY() == worker.getPosition().getY() + 1 || cell.getY() == worker.getPosition().getY()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if a level could be built in a cell.
     * @param cell
     * The cell where it has to be placed the level.
     * @param worker
     * The worker that has to build.
     * @param level
     * The level that has to be built.
     */
    public boolean isFeasibleBuild(Cell cell, Worker worker, Level level){

        if(cell.getWorker()!=null) {
            return false;
        }

        if(cell.getLevel()==Level.DOME){
            return false;
        }

        if(!(cell.getLevel().ordinal()==(level.ordinal()-1))){
            return false;
        }

        if(cell.getX() == worker.getPosition().getX() - 1 || cell.getX() == worker.getPosition().getX() + 1 || cell.getX() == worker.getPosition().getX()) {
            if (cell.getY() == worker.getPosition().getY() - 1 || cell.getY() == worker.getPosition().getY() + 1 || cell.getY() == worker.getPosition().getY()) {
                return true;
            }
        }
        return false;
    }

    public int getAvailableMoveNumber() {
        return availableMoveNumber;
    }

    public int getAvailableBuildNumber() {
        return availableBuildNumber;
    }

    public void setAvailableMoveNumber(int availableMoveNumber) {
        this.availableMoveNumber = availableMoveNumber;
    }

    public void setAvailableBuildNumber(int availableBuildNumber) {
        this.availableBuildNumber = availableBuildNumber;
    }

    public void setCanMoveUp(boolean canMoveUp) {
        this.canMoveUp = canMoveUp;
    }

    public boolean getCanMoveUp() {
        return canMoveUp;
    }

    public boolean getHasMoved() {
        return hasMoved;
    }

    public void setHasMoved(boolean hasMoved) {
        this.hasMoved = hasMoved;
    }

    public boolean getHasBuilt() {
        return hasBuilt;
    }

    public void setHasBuilt(boolean hasBuilt) {
        this.hasBuilt = hasBuilt;
    }

    /**
     * Checks if a player has won.
     * @param oldLevel
     * The old level.
     * @param newLevel
     * The new level.
     * @return
     * If he was won or not.
     */
    public boolean winControl(Level oldLevel, Level newLevel){
        if(oldLevel!=newLevel&&newLevel==Level.LEVEL3){
            return true;
        }
        return false;
    }

    /**
     * It resets the available actions at the beginning of the turn.
     */
    public void resetTurn(){
        this.availableMoveNumber = 1;
        this.availableBuildNumber = 0;
        this.canMoveUp = true;
        this.hasMoved = false;
        this.hasBuilt = false;
    }


}